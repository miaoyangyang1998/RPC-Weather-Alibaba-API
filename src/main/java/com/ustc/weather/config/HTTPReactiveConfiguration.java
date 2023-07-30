package com.ustc.weather.config;

import com.ustc.weather.service.WeatherHTTPInterfaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author :Yangyang Miao
 * @date :2023-07-30 10:21:00
 */
@Configuration
public class HTTPReactiveConfiguration {

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory() {
        // 1.创建客户端
        WebClient client = WebClient.builder()
                //.baseUrl("https://ali-weather.showapi.com")
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer
                            .defaultCodecs()
                            .maxInMemorySize(256 * 1024 * 1024);
                    // 响应式数据量太大有可能会超出BufferSize，所以这里设置的大一点
                }).build();
        // 2.创建工厂
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
    }

    @Bean
    WeatherHTTPInterfaceService weatherHTTPService(HttpServiceProxyFactory factory) {
        // 3.获取Service代理对象
        WeatherHTTPInterfaceService weatherAPI = factory.createClient(WeatherHTTPInterfaceService.class);
        return weatherAPI;
    }
}
