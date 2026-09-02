package Rios.tech.Noctra;

import Rios.tech.Noctra.api.config.TmdbConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TmdbConfig.class)
public class NoctraApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoctraApplication.class, args);
	}

}
