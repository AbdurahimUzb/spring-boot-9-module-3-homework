package general.springboothomework.yaml;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "yaml")
public class YamlProperties {

    private PersonYaml per;
    private List<PersonYaml> persons;

}
