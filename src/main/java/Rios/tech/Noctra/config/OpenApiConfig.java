package Rios.tech.Noctra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI noctraApi() {

        return new OpenAPI()

                .info(

                        new Info()

                                .title("Noctra API")

                                .version("1.0")

                                .description("Backend de la plataforma de streaming Noctra")

                                .contact(

                                        new Contact()

                                                .name("Sebastián Ríos")

                                                .email("")

                                )

                );
    }

}