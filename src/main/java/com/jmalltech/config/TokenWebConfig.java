package com.jmalltech.config;

import com.jmalltech.web.resolver.ClientIdMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class TokenWebConfig implements WebMvcConfigurer {
    private final ClientIdMethodArgumentResolver clientIdMethodArgumentResolver;

    public TokenWebConfig(ClientIdMethodArgumentResolver clientIdMethodArgumentResolver) {
        this.clientIdMethodArgumentResolver = clientIdMethodArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(clientIdMethodArgumentResolver);
    }
}
