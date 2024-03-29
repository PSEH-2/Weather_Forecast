package com.weather.org.weatherforecast.configurations;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConfig {

    @Value("${weather.forecast.url}")
    private String weatherUrl;

    @Value("${weather.forecast.days}")
    private int weatherForecastDays;


}