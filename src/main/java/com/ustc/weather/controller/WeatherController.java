package com.ustc.weather.controller;

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

    @GetMapping("/weather")
    public Mono<String> weather(@RequestParam("city") String city) {
        return weatherService.getWeather(city);
    }
}
