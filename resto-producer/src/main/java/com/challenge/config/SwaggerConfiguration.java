package com.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

  @Value("${project.version}")
  private String version;

  /**
   * Swagger docket configuration.
   */
  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
        .apis(RequestHandlerSelectors.basePackage("com.truenorth")).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Restaurant delivery API")
        .description("Restaurant management services.").version(version).build();
  }
}
