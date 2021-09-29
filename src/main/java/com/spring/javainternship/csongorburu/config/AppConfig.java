package com.spring.javainternship.csongorburu.config;

import com.spring.javainternship.csongorburu.utility.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public Mapper mapper() {
    return new Mapper();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Execom internship")
        .description("REST service for posting ideas.")
        .version("1.0.0")
        .contact(new Contact("Buru Csongor", "burucs.com", "burcy@pm.me"))
        .build();
  }
}