package com.weather.org.weatherforecast.provider;

import com.weather.org.weatherforecast.configurations.AppConfig;
import com.weather.org.weatherforecast.models.CityForecastModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Repository
public class WeatherForecastProvider {

    private Logger logger = LoggerFactory.getLogger(WeatherForecastProvider.class);

    private RestTemplate restTemplate;

    private AppConfig appConfig;

    public WeatherForecastProvider(RestTemplate restTemplate, AppConfig appConfig) {
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
    }


    public CityForecastModel getWeatherForecastForCity(String location) {

        ResponseEntity<CityForecastModel> response;
        String weatherRequestUrl = String.format(appConfig.getWeatherUrl(), location);
        logger.info("Calling Weather Report api to fetch response for: ''", weatherRequestUrl);
        response = restTemplate.getForEntity(weatherRequestUrl, CityForecastModel.class);
        if (response.getStatusCode().is2xxSuccessful() && !ObjectUtils.isEmpty(response)) {
            logger.info("Received response from Weather Report api: ''", response.toString());
            return response.getBody();
        }
        return null;
    }
}

