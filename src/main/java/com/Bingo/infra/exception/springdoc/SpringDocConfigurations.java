package com.Bingo.infra.exception.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfigurations {

   @Bean
   public OpenAPI customOpenApi() {
	   return new OpenAPI().components(new Components())
			   .info(new Info()
               .title("Bingão")
               .description("API Rest da aplicação do Bingão para diciplina de Engenharia de softwaere")
               .contact(new Contact()
                       .name("Time Backend")
                       .email("saul.batista@arapiraca.ufal.br"))
               );
   }

}
