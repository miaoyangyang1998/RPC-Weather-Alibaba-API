package com.ustc.weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

/**
 * @author :Yangyang Miao
 * @date :2023-07-30 10:03:00
 */
@Service
public class WeatherHTTPServiceFunction {

    @Value("${header.value}")
    private String headerValue;

    public Mono<String> getHTTPWeather02(String city) {
        // 1.创建客户端
        WebClient client = WebClient.builder()
                .baseUrl("https://ali-weather.showapi.com")
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer
                            .defaultCodecs()
                            .maxInMemorySize(256 * 1024 * 1024);
                    // 响应式数据量太大有可能会超出BufferSize，所以这里设置的大一点
                }).build();
        // 2.创建工厂
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();

        // 3.获取代理对象
        WeatherHTTPService weatherAPI = factory.createClient(WeatherHTTPService.class);
        return weatherAPI.getWeather02(city, "APPCODE " + headerValue);
    }
}
