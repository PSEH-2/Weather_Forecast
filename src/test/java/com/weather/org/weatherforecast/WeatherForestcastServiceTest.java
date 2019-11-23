//package com.weather.org.weatherforecast;
//
//import com.weather.org.weatherforecast.configurations.AppConfig;
//import com.weather.org.weatherforecast.models.CityForecastModel;
//import com.weather.org.weatherforecast.models.WeatherResponse;
//import com.weather.org.weatherforecast.provider.WeatherForecastProvider;
//import com.weather.org.weatherforecast.services.WeatherForecastService;
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.util.List;
//
//
//import static io.github.benas.randombeans.api.EnhancedRandom.random;
//
//import static org.mockito.Mockito.when;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class WeatherForestcastServiceTest {
//
//    @InjectMocks
//    WeatherForecastService weatherForecastService;
//
//    @Mock
//    WeatherForecastProvider weatherForecastProvider;
//
//    @Mock
//    AppConfig appConfig;
//
//    @Ignore
//    @Test
//    public void testGetWeatherForCityValidResponse() {
//        CityForecastModel cityForecastModel = random(CityForecastModel.class);
//        when(weatherForecastProvider.getWeatherForecastForCity(Mockito.anyString())).thenReturn(cityForecastModel);
//        when(appConfig.getWeatherForecastDays()).thenReturn(3);
//        List<WeatherResponse> weatherResponses = weatherForecastService.getWeatherForCity("testloc");
//        Assert.assertNotNull(weatherResponses);
//    }
//
//    @Ignore
//    @Test(expected = HttpClientErrorException.class)
//    public void testGetWeatherForCityNotFoundResponse() {
//        when(weatherForecastProvider.getWeatherForecastForCity(Mockito.anyString())).thenThrow(HttpClientErrorException.class);
//        weatherForecastService.getWeatherForCity("testloc");
//
//    }
//
//    @Ignore
//    @Test
//    public void testGetWeatherForCityNullResponse() {
//        when(weatherForecastProvider.getWeatherForecastForCity(Mockito.anyString())).thenReturn(null);
//        List<WeatherResponse> weatherResponses = weatherForecastService.getWeatherForCity("testloc");
//        Assert.assertNull(weatherResponses);
//
//    }
//
//}
