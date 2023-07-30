package com.ustc.weather.controller;

import com.ustc.weather.service.WeatherHTTPServiceFunction;
import com.ustc.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author :Yangyang Miao
 * @date :2023-07-29 23:40:00
 */
@RestController
@RequestMapping
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherHTTPServiceFunction weatherHTTPServiceFunction;

    @GetMapping("/weather")
    public Mono<String> weather(@RequestParam("city") String city) {
        return weatherService.getWeather01(city);
    }

    @GetMapping("/weatherhttp")
    public Mono<String> weatherHttp(@RequestParam("city") String city) {
        return weatherHTTPServiceFunction.getHTTPWeather02(city);
    }
}
