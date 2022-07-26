package com.sigabem.shipping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SpringFoxConfig {
  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.sigabem.shipping"))
        .paths(regex("/api.*"))
        .build()
        .apiInfo(metaInfo());
  }

  private ApiInfo metaInfo() {
    ApiInfo apiInfo =
        new ApiInfo(
            "Sigabem",
            "API for calculate of deadline and price of shipping.",
            "1.0",
            "",
            "",
            "",
            "");
    return apiInfo;
  }
}
