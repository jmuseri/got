package ar.com.bbva.got.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket parametriaApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Parametria").select()
                .apis(RequestHandlerSelectors.basePackage("ar.com.bbva.got.controller.parametria")).paths(regex("/*.*"))
                .build().apiInfo(metaData());
        // .paths(regex("/parametria.*")).build().apiInfo(metaData());
    }

     @Bean public Docket funcionalApi() { return new
     Docket(DocumentationType.SWAGGER_2).groupName("Funcional").select()
     .apis(RequestHandlerSelectors.basePackage(
     "ar.com.bbva.got.controller.funcional")).paths(regex("/*.*"))
     .build().apiInfo(metaData());}
    //  .paths(regex("/funcional.*")).build().apiInfo(metaData()); }
     

    private ApiInfo metaData() {
        return new ApiInfoBuilder().title("GOT - Gestion Online de Tramites - API RESTful")
                .description("\"Gestion Online de Tramites Services \"").version("0.2.4")
                .license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
