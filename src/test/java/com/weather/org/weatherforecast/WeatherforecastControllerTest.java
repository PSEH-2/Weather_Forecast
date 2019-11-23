package com.weather.org.weatherforecast;

import com.weather.org.weatherforecast.controller.WeatherForecastController;
import com.weather.org.weatherforecast.services.WeatherForecastService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class WeatherforecastControllerTest {

    @InjectMocks
    WeatherForecastController weatherForecastController;

    @Mock
    WeatherForecastService weatherForecastService;

	@Test
    public void testGetValidResponseForValidLocation(){


    }
}
