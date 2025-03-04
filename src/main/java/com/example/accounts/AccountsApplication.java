package com.example.accounts;

import com.example.accounts.Audit.AuditImpl;
import com.example.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(AccountsContactInfoDto.class)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts Microservice", version = "1.0", description = "Accounts Microservice",
		contact = @Contact(name = "Sandeep", email= "sandeepn6262@gmail.com"),
license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")),
externalDocs = @ExternalDocumentation(description = "please refer tothis documentation", url = "https://www.google.com"))
@EnableFeignClients
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
		System.out.println("Hello ");
	}

}
