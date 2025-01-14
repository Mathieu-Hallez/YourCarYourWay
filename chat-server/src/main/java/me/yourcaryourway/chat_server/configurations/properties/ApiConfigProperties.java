package me.yourcaryourway.chat_server.configurations.properties;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "api")
@ConfigurationPropertiesScan
public record ApiConfigProperties(@NotNull String baseUrl) {
}
