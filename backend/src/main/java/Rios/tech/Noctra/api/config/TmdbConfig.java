package Rios.tech.Noctra.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tmdb")
public record TmdbConfig(
        String baseUrl,
        String token,
        String imageBaseUrl
) {
}