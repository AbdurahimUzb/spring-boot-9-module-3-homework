package general.springboothomework.yaml;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/yaml")
public class PersonController {

    private final YamlProperties yamlProperties;

    public PersonController(YamlProperties yamlProperties) {
        this.yamlProperties = yamlProperties;
    }

    @GetMapping("/getPerson")
    public PersonYaml getPerson() {
        return yamlProperties.getPer();
    }

    @GetMapping("/getPersons()")
    public List<PersonYaml> getPersons() {
        return yamlProperties.getPersons();
    }

}
