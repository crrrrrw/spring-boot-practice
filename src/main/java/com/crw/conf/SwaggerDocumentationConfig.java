package com.crw.conf;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
@Data
@Slf4j
public class SwaggerDocumentationConfig {
    private boolean enable = true;
    private String title;
    private String description;
    private String license;
    private String licenseUrl;
    private String termsOfServiceUrl;
    private String version;
    private String basePackage;

    @Autowired
    private ApplicationContext applicationContext;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(license)
                .licenseUrl(licenseUrl)
                .termsOfServiceUrl(termsOfServiceUrl)
                .version(version)
                .build();
    }

    @Bean
    public Docket customImplementation() {
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();

        if (profiles.length > 0) {
            String profile = profiles[0];

            // 配置生产环境
            if (profile.endsWith("product")) {
                log.info("禁止使用swagger");
                enable = false;
                return null;
            }
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}
