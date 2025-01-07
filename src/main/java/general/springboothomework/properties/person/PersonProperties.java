package general.springboothomework.properties.person;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "person")
public class PersonProperties {

    private Long id;
    private String name;
    private Integer age;

    public Person getPerson() {
        return new Person(id, name, age);
    }
}
