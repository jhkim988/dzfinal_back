package com.douzone.dzfinal.config;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

    private final String version = "V1";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("dzfinal API")
                .description("Swagger ??????!!")
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(version)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.douzone.dzfinal"))
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.any())
                .build();
    }
    
    @Bean
    public Docket commonApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V2")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.douzone.dzfinal"))
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.any())
                .build();
    }
}


//@Configuration	// ????????? ????????? ???????????? ??????????????? ?????? ??????????????? 
//@EnableSwagger2	// Swagger2??? ?????????????????? ??????????????? 
//@SuppressWarnings("unchecked")	// warning?????? ????????? ?????? ?????? 
//public class SwaggerConfig extends WebMvcConfigurationSupport {
//
//	//????????? ????????? ???
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}
//
//
//    // API?????? ???????????? ?????? ??????.
//	@Bean
//    public Docket productApi() {
//        return getDocket("??????", Predicates.or(
//                PathSelectors.regex("/user.*")));
//    }
//
//    
//    @Bean
//    public Docket searchApi() {
//        return getDocket("??????", Predicates.or(
//                PathSelectors.regex("/reservation.*")));
//    }
//
//    
//    @Bean
//    public Docket commonApi() {
//        return getDocket("??????", Predicates.or(
//                PathSelectors.regex("/test.*")));
//        		
//    }
//
//    @Bean
//    public Docket allApi() {
//        return getDocket("??????", Predicates.or(
//                PathSelectors.regex("/*.*")));
//    }
//    
//    //swagger ??????.
//    public Docket getDocket(String groupName, Predicate<String> predicate) {
//        return new Docket(DocumentationType.SWAGGER_2)
//        		.groupName(groupName)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.douzone.dzfinal"))
//                .paths(PathSelectors.ant("/V1/**"))
//                .apis(RequestHandlerSelectors.any())
//                .build();
//    }
//    
//    //swagger ui ??????.
//    @Bean
//    public UiConfiguration uiConfig() {
//        return UiConfigurationBuilder.builder()
//                .displayRequestDuration(true)
//                .validatorUrl("")
//                .build();
//    }
//
//		private List<ResponseMessage> getCustomizedResponseMessages() {
//		    List<ResponseMessage> responseMessages = new ArrayList<>();
//		    responseMessages.add(new ResponseMessageBuilder().code(200).message("??????").build());
//		    responseMessages.add(new ResponseMessageBuilder().code(204).message("????????? ?????????").build());
//		    responseMessages.add(new ResponseMessageBuilder().code(400).message("????????? ??????").build());
//		    responseMessages.add(new ResponseMessageBuilder().code(401).message("??? ?????????").build());
//		    responseMessages.add(new ResponseMessageBuilder().code(403).message("????????????").build());
//		    responseMessages.add(new ResponseMessageBuilder().code(412).message("????????? ??????").build());
//		    responseMessages.add(new ResponseMessageBuilder().code(500).message("????????????").build());
//		    return responseMessages;
//		}
//	
//}




// ?????????
//@Configuration
//@EnableSwagger2
//@EnableWebMvc
//public class SwaggerConfig {
//	private String version;
//  private String title;
//  private final String TITLE_FIX = "DZ project 04 API";
//	
//  @Bean
//  Docket apiV1() {
//      version = "V1";
//      title = TITLE_FIX + version;
//
//      return new Docket(DocumentationType.SWAGGER_2)
//              .useDefaultResponseMessages(false)
//              .select()
//              .apis(RequestHandlerSelectors.basePackage("com.douzone.dzfinal"))
//              .paths(PathSelectors.ant("/V1/**"))
//              .build()
//              .apiInfo(getApiInfo(title, version))
//              .securitySchemes(Collections.singletonList(getApiKey()))
//              .enable(true);
//  }
//	
//	private ApiInfo getApiInfo(String title, String version) {
//      return new ApiInfo(
//              title,
//              "Swagger API Docs",
//              version,
//              "com.douzone.dzfinal",
//              new Contact("dzfinal", "com.douzone", "skdlfxp135@naver.com"),
//              "Licenses",
//              "com.douzone.dzfinal",
//              new ArrayList<>()
//      );
//  }
//
//  private ApiKey getApiKey () {
//      return new ApiKey("jwtToken", "X-AUTH-TOKEN", "header");
//  }
//  
//  private List<ResponseMessage> getCustomizedResponseMessages() {
//      List<ResponseMessage> responseMessages = new ArrayList<>();
//      responseMessages.add(new ResponseMessageBuilder().code(200).message("??????").build());
//      responseMessages.add(new ResponseMessageBuilder().code(204).message("????????? ?????????").build());
//      responseMessages.add(new ResponseMessageBuilder().code(400).message("????????? ??????").build());
//      responseMessages.add(new ResponseMessageBuilder().code(401).message("??? ?????????").build());
//      responseMessages.add(new ResponseMessageBuilder().code(403).message("????????????").build());
//      responseMessages.add(new ResponseMessageBuilder().code(412).message("????????? ??????").build());
//      responseMessages.add(new ResponseMessageBuilder().code(500).message("????????????").build());
//      return responseMessages;
//  }
//}