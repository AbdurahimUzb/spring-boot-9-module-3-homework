package general.springboothomework.properties.person.controller;

import general.springboothomework.properties.person.Person;
import general.springboothomework.properties.person.PersonProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonProperties personProperties;
    
    @Value("${person.id}")
    private Long personId;

    @Value("${person.name}")
    private String personName;

    @Value("${person.age}")
    private Integer personAge;

    public PersonController(PersonProperties personProperties) {
        this.personProperties = personProperties;
    }

    @GetMapping("/@Value")
    public Person getPerson() {
        return new Person(personId, personName, personAge);
    }

    @GetMapping("/@ConfigurationProperties")
    public Person getConfigurationProperties() {
        return personProperties.getPerson();
    }
}
