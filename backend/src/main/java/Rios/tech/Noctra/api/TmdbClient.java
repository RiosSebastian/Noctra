package Rios.tech.Noctra.api;

import Rios.tech.Noctra.api.config.TmdbConfig;
import Rios.tech.Noctra.api.dto.TmdbMovieDto;
import Rios.tech.Noctra.api.dto.TmdbMovieResponse;
import Rios.tech.Noctra.exception.TmdbUnavailableException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class TmdbClient {

    private final RestClient restClient;

    public TmdbClient(TmdbConfig config) {

        this.restClient = RestClient.builder()
                .baseUrl(config.baseUrl())
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + config.token()
                )
                .defaultHeader(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .build();
    }

    public TmdbMovieResponse getPopularMovies(int page) {

        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/movie/popular")
                            .queryParam("language", "es-ES")
                            .queryParam("page", page)
                            .build()
                    )
                    .retrieve()
                    .body(TmdbMovieResponse.class);
        } catch (RestClientException e) {
            // Cubre timeouts, 401 por token vencido/mal configurado, rate limit (429),
            // TMDB caído, etc. Sin esto, cualquiera de esos casos tira un 500 crudo.
            throw new TmdbUnavailableException("No se pudo obtener el catálogo de TMDB", e);
        }
    }

    public TmdbMovieDto getMovieDetails(Long id) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/movie/{id}")
                            .queryParam("language", "es-ES")
                            .build(id)
                    )
                    .retrieve()
                    .body(TmdbMovieDto.class);
        } catch (RestClientException e) {
            throw new TmdbUnavailableException("No se pudo obtener el detalle de la película", e);
        }
    }

    public TmdbMovieResponse searchMovies(String query, int page) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search/movie")
                            .queryParam("language", "es-ES")
                            .queryParam("query", query)
                            .queryParam("page", page)
                            .build()
                    )
                    .retrieve()
                    .body(TmdbMovieResponse.class);
        } catch (RestClientException e) {
            throw new TmdbUnavailableException("No se pudo buscar en TMDB", e);
        }
    }
}