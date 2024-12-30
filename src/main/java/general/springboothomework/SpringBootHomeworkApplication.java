package general.springboothomework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import general.springboothomework.entity.ItemEntity;
import general.springboothomework.entity.StoreEntity;
import general.springboothomework.entity.UserEntity;
import general.springboothomework.repository.ItemEntityRepository;
import general.springboothomework.repository.StoreEntityRepository;
import general.springboothomework.repository.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SpringBootHomeworkApplication {

    private final PasswordEncoder passwordEncoder;
    private final ItemEntityRepository itemEntityRepository;
    private final StoreEntityRepository storeEntityRepository;
    private final UserEntityRepository userEntityRepository;

    public SpringBootHomeworkApplication(PasswordEncoder passwordEncoder, ItemEntityRepository itemEntityRepository, StoreEntityRepository storeEntityRepository, UserEntityRepository userEntityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.itemEntityRepository = itemEntityRepository;
        this.storeEntityRepository = storeEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHomeworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ObjectMapper mapper) throws IOException {

        File itemUrl = new File("src/main/java/general/springboothomework/data/item.json");
        File storeUrl = new File("src/main/java/general/springboothomework/data/store.json");
        File userUrl = new File("src/main/java/general/springboothomework/data/user.json");

        return args -> {
            List<ItemEntity> itemEntities = mapper.readValue(itemUrl, new TypeReference<>() {
            });
            List<StoreEntity> storeEntities = mapper.readValue(storeUrl, new TypeReference<>() {
            });
            List<UserEntity> userEntities = mapper.readValue(userUrl, new TypeReference<>() {
            });
            itemEntityRepository.saveAll(itemEntities);
            storeEntityRepository.saveAll(storeEntities);

//            TODO: Users password larini BCryptPasswordEncoder orqali shriftlab saqlyabman!
            userEntities.forEach(user -> {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userEntityRepository.save(user);
            });
        };
    }
}
