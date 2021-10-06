package br.com.siberius.realmeet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
            .info(apiInfo());
    }


    public Info apiInfo() {
        Contact contato = new Contact().name("Siberius Real Meet")
            .url("https://www.siberius.com.br").email("jhonnyscerni@gmail.com");

        return new Info()
            .title("API do sistema Real Meet")
            .description(" Autor : Jhonny Scerni Gondim Costa ")
            .version("1")
            .contact(contato);
    }
}
