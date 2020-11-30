package org.frekele.nikoday.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Log4j2
@Configuration
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SwaggerConfig implements Serializable {

    @Bean
    public OpenAPI customOpenAPI(@Value("${info.app.description}") String appDescription, @Value("${info.app.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("NikoDay API")
                        .version(appVersion)
                        .description(appDescription)
                        .contact(new Contact().name("Leandro Kersting de Freitas").email("leandro@frekele.org").url("https://github.com/frekele"))
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache License 2.0").url("https://github.com/nikoday/nikoday-backend/blob/main/LICENSE")));
    }
}
