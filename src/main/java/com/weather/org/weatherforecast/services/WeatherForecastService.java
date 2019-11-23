package com.weather.org.weatherforecast.services;

import com.weather.org.weatherforecast.configurations.AppConfig;
import com.weather.org.weatherforecast.models.CityForecastModel;
import com.weather.org.weatherforecast.models.DailyForecast;
import com.weather.org.weatherforecast.models.WeatherResponse;
import com.weather.org.weatherforecast.provider.WeatherForecastProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class WeatherForecastService {

    private Logger logger = LoggerFactory.getLogger(WeatherForecastService.class);

    private final WeatherForecastProvider weatherForecastProvider;

    private final AppConfig appConfig;

    public WeatherForecastService(WeatherForecastProvider weatherForecastProvider, AppConfig appConfig) {
        this.weatherForecastProvider = weatherForecastProvider;
        this.appConfig = appConfig;
    }

    public List<WeatherResponse> getWeatherForCity(String location) {

        logger.info("Calling WeatherForecastProvider for city:''",location);
        CityForecastModel cityForecastModel = weatherForecastProvider.getWeatherForecastForCity(location);
        List<WeatherResponse> weatherResponses = null;
        if (!ObjectUtils.isEmpty(cityForecastModel)) {
            logger.info(("Received response from Weatherforecast provider: ''"),cityForecastModel.toString());
            weatherResponses = getWeatherFor(appConfig.getWeatherForecastDays(), cityForecastModel);
        }
        return weatherResponses;
    }

    private List<WeatherResponse> getWeatherFor(int days, CityForecastModel cityForecastModel) {

        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusDays(days-1);

        List<DailyForecast> dailyForecastList = cityForecastModel.getList().
                stream().
                filter(dailyForecast -> (!dailyForecast.getDate().isBefore(startDate)) && (!dailyForecast.getDate().isAfter(endDate))).collect(Collectors.toList());



        List<DailyForecast> dailyForecastListTemp = dailyForecastList.
                stream().
                filter(i -> i.getMain().getTemperature() > 280.00).
                collect(Collectors.toList());

        List<DailyForecast> dailyForecastListWeather =
                dailyForecastList.stream().filter(dailyForecast -> dailyForecast.getWeather().get(0).getMain().equals("Rain")).collect(Collectors.toList());

        List<DailyForecast> dailyForecasts = new ArrayList<>();
        dailyForecasts.addAll(dailyForecastListTemp);
        dailyForecasts.addAll(dailyForecastListWeather);

        List<DailyForecast> dailyForecastsFiltered = dailyForecasts.stream().filter(distinctByKey(dailyForecast -> dailyForecast.getDate())).collect(Collectors.toList());

        List<WeatherResponse> weatherResponses = new ArrayList<>();

        for (DailyForecast dailyForecast :
                dailyForecastsFiltered) {
            String userTip = (dailyForecast.getWeather().get(0).getMain().equals("Rain")) ? "Carry umberlla" : "Carry Sunscreen";
            WeatherResponse weatherResponse = new WeatherResponse();
            weatherResponse.setDate(dailyForecast.getDate());
            weatherResponse.setTemperature(dailyForecast.getMain().getTemperature());
            weatherResponse.setWeather(dailyForecast.getWeather().get(0).getMain());
            weatherResponse.setUser_tip(userTip);
            weatherResponses.add(weatherResponse);
        }

        return weatherResponses;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}

