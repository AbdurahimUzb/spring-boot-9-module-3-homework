package general.springboothomework;

import general.springboothomework.properties.person.PersonProperties;
import general.springboothomework.yaml.YamlProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PersonProperties.class, YamlProperties.class})
public class SpringBootHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHomeworkApplication.class, args);
    }

}
