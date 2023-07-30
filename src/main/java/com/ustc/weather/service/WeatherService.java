package com.ustc.weather.service;

import reactor.core.publisher.Mono;

/**
 * @author :Yangyang Miao
 * @date :2023-07-29 23:46:00
 */
public interface WeatherService {

    public Mono<String> getWeather01(String city);
}
