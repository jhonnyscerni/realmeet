package br.com.siberius.realmeet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
            .title("API do sistema Real Meet")
            .version("1")
            .version("2.0")
            .contact(apiContact())
            .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
            .name("MIT Licence")
            .url("https://opensource.org/licenses/mit-license.php");
    }

    private Contact apiContact() {
        return new Contact().name("Siberius Real Meet")
            .url("https://www.siberius.com.br").email("jhonnyscerni@gmail.com");
    }
}
