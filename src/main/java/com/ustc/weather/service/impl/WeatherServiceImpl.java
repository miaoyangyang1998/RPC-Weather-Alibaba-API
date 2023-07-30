package com.ustc.weather.service.impl;

import com.ustc.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :Yangyang Miao
 * @date :2023-07-29 23:47:00
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.uri}")
    private String weatherUri;

    @Value("${header.name}")
    private String headerName;

    @Value("${header.value}")
    private String headerValue;

    @Value("${queryparam.key.area}")
    private String queryParamKeyArea;


    @Override
    public Mono<String> getWeatherBySimpleWebClient(String city) {
        // 远程调用阿里云API
        // 1.创建WebClient
        WebClient client = WebClient.create();

        Map<String, String> params = new HashMap<>();
        params.put("area", city);
        // 2.定义发请求的行为，get出去后接受一个JSON数据（定义响应的数据类型）
        // 此处的get也可以改成post等其他请求
        // params为请求参数
        Mono<String> resultJson = client.get()
                .uri(weatherUri + "?" + queryParamKeyArea + "={area}", params)
                .accept(MediaType.APPLICATION_JSON)
                .header(headerName, "APPCODE " + headerValue)
                .retrieve()
                .bodyToMono(String.class);
        return resultJson;
    }
}
