package general.springboothomework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import general.springboothomework.entity.AuthUser;
import general.springboothomework.repository.AuthUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class SpringBootHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHomeworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ObjectMapper mapper, AuthUserRepository authUserRepository) {
        return args -> {
            File file = new File("src/main/MOCK_DATA.json");
            List<AuthUser> users = mapper.readValue(file, new TypeReference<List<AuthUser>>() {
            });
            authUserRepository.saveAll(users);
        };
    }

}
