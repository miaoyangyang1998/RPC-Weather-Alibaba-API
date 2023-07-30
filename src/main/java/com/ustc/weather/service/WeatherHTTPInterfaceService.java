package com.ustc.weather.service;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

/**
 * @author :Yangyang Miao
 * @date :2023-07-30 00:54:00
 */
public interface WeatherHTTPInterfaceService {

    @GetExchange(url = "https://ali-weather.showapi.com/area-to-weather-date", accept = "application/json")
    public Mono<String> getWeatherByHTTPInterface(@RequestParam("area") String city, @RequestHeader("Authorization") String auth);
}
