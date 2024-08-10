package com.system.remedios.openApi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api Pharmacy")
                        .version("1.0.0")
                        .description("Api for controller of pharmacy and stock data of remedy")
                        .termsOfService("https://github.com/allisoms/pharmacy")
                        .contact(new Contact()
                                .name("Support for email")
                                .email("allisonsou10261@gmail.com"))
                        .license(new License()
                                .name("APACHE 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
                ).components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                ).security(Collections.singletonList(new SecurityRequirement().addList("bearerAuth")));
    }
}
