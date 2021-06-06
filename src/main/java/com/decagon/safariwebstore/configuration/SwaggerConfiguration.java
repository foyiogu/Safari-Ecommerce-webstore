package com.decagon.safariwebstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

    // WEBSITE IS - http://localhost:8080/swagger-ui/

    @Bean
    public Docket swaggerConfig() {
        //Return a prepared Docket Instance
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.decagon.safariwebstore"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiDetails())
                .securitySchemes(securitySchemes()).securityContexts(List.of(securityContext()));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui/")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private List<SecurityScheme> securitySchemes() {
        return List.of(apiKey());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(List.of(bearerAuthReference())).forPaths(PathSelectors.any()).build();
    }

    private SecurityReference bearerAuthReference() {
        return new SecurityReference("Bearer", new AuthorizationScope[0]);
    }


    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Safari Web Store API",
                "API for Safari web store by Decagon",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Decagon", "http://decagonhq.com", "info@decagondq.com"),
                "API License",
                "https://decagonhq.com/terms.php",
                Collections.emptyList()
        );
    }


    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }


}
