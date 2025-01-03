package general.springboothomework;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Swagger example work",
                version = "10",
                contact = @Contact(
                        name = "Rixsiboyev Abdurahim", email = "rixsiboyevabdurahim215@gamil.com"
                ),
                license = @License(
                        name = "Apache 2.0"
                ),
                termsOfService = "https://swagger.io/terms/",
                description = "This Document designed for OpenApi project"
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringBootHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHomeworkApplication.class, args);
    }

}
