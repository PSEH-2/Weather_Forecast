package com.weather.org.weatherforecast.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Weather {

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;
}