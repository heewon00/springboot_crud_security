package com.kt.edu.thirdproject.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String group;

    @Value("${app-info.title}")
    private String title;

    @Value("${app-info.desc}")
    private String desc;

    @Value("${app-info.build.version}")
    private String version;

    @Value("${app-info.license}")
    private String license;

    @Value("${app-info.license-url}")
    private String licenseUrl;

    @Value("${app-info.doc-desc}")
    private String docDesc;

    @Value("${app-info.doc-url}")
    private String docUurl;

    @Profile({"local", "dev"})
    @Bean
    public GroupedOpenApi openApi() {
        String[] paths = {"/**"};
        return GroupedOpenApi.builder().group(group).pathsToMatch(paths).build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authoriztaion");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement))
                .info(new Info().title(title)
                        .description(desc)
                        .version(version)
                        .license(new License().name(license).url(licenseUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description(docDesc)
                        .url(docUurl));
    }

}

