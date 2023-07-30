package com.ustc.weather.controller;

import com.ustc.weather.service.WeatherHTTPInterfaceService;
import com.ustc.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${header.value}")
    private String headerValue;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherHTTPInterfaceService weatherAPI;

    @GetMapping("/weather01")
    public Mono<String> weatherBySimpleWebClient(@RequestParam("city") String city) {
        return weatherService.getWeatherBySimpleWebClient(city);
    }

    @GetMapping("/weather02")
    public Mono<String> weatherByHttpInterface(@RequestParam("city") String city) {
        return weatherAPI.getWeatherByHTTPInterface(city, "APPCODE " + headerValue);
    }
}
