package general.springboothomework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import general.springboothomework.model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class SpringBootHomeworkApplicationTests {

    public static final ObjectMapper mapper = new ObjectMapper();
    public static final File YAML = new File("src/test/java/general/springboothomework/file/write.yaml");
    public static final File PERSON_JSON_FILE = new File("src/test/java/general/springboothomework/file/person.json");
    public static final File XML_FILE = new File("src/test/java/general/springboothomework/file/persons.xml");

    public List<Car> cars = new ArrayList<>() {{
        add(new Car("red", "Ferrari"));
        add(new Car("yellow", "Porsche"));
        add(new Car("white", "BMW"));
        add(new Car("black", "Mercedes"));
        add(new Car("green", "Lamborghini"));
    }};

    @Test
    void methodCarsWriteFileToJson() throws Exception {
        mapper.writeValue(new FileOutputStream("src/test/java/general/springboothomework/file/cars.json"), cars);
        System.out.println("Cars saved to file");
    }

    @Test
    void methodCarsReadFileFromJson() throws Exception {
        List<Car> carList = mapper.readValue(new File("src/test/java/general/springboothomework/file/cars.json"), new TypeReference<>() {
        });
        carList.forEach(System.out::println);
    }

    @Test
    void notValidField() throws Exception {
        String car1 = """
                {
                    "id": 1,
                    "color": "red",
                    "type": "Ferrari"
                  },
                """;
        System.out.println(mapper.readValue(car1, Car2.class));
        System.out.println("==================================================");

//        TODO Owner topilmasa ignore qilinishiga Example!
        String car2 = """
                {
                    "id": 2,
                    "unknown": "unknown_Field",
                    "type": "Lamborghini"
                  },
                """;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println(mapper.readValue(car2, Car2.class));
    }


    @Test
    void yamlFileWriteYamlFile() throws Exception {
        var yaml = new YAMLFactory();
        var yamlMapper = new ObjectMapper(yaml);
        yamlMapper.registerModule(new JavaTimeModule());

        Transaction transaction = Transaction.builder()
                .id(1L)
                .amount(new BigDecimal("1000.00"))
                .status(Status.SUCCESS)
                .createdDate(LocalDateTime.now().minusMonths(1).minusDays(1))
                .updatedDate(LocalDateTime.now())
                .build();

        yamlMapper.writeValue(YAML, transaction);
        System.out.println("Transaction saved to file as YAML");
    }

    @Test
    void yamlFileReadTransaction() throws Exception {
        var yaml = new YAMLFactory();
        var yamlMapper = new ObjectMapper(yaml);
        yamlMapper.registerModule(new JavaTimeModule());

        yamlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var transaction = yamlMapper.readValue(YAML, Transaction.class);
        System.out.println(transaction);
    }

    @Test
    void jsonNodeExample() throws Exception {
        LocalDateTime minusDays = LocalDateTime.now().minusMonths(1).minusDays(1);
        LocalDateTime currentTime = LocalDateTime.now();

        String json = """
                {
                "id": 1,
                "amount": 7534675.43,
                "status": "%s",
                "createdDate": "%s",
                "updatedDate": "%s"
                }
                """.formatted(Status.SUCCESS.name(), minusDays, currentTime);

        JsonNode jsonNode = new ObjectMapper().readTree(json);
        String amount = jsonNode.findValue("amount").asText();
        String status = jsonNode.findValue("status").asText();

        System.out.println("amount = " + amount);
        System.out.println("status = " + status);
    }

    @Test
    void personWriteJsonFile() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Person.PersonBuilder johnDoe = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .age(25);

        mapper.writeValue(PERSON_JSON_FILE, johnDoe.build());
        System.out.println("John Doe saved to Person.json File");
    }

    @Test
    void personReadJsonFile() throws Exception {
        Person person = mapper.readValue(PERSON_JSON_FILE, Person.class);
        System.out.println(person);
        System.out.println("John Doe read to Person.json File");
    }

    @Test
    void personsWriteXmlFile() throws Exception {
        XmlMapper xmlMapper = new XmlMapper();

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Ali", "Aliyev", 17));
        persons.add(new Person("Vali", "Valiyev", 20));
        persons.add(new Person("Bolta", "Boltayev", 26));

        xmlMapper.writeValue(XML_FILE, persons);

        System.out.println("Persons saved to persons.xml File");
    }

    @Test
    void personsReadXmlFile() throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        List<Person> persons = xmlMapper.readValue(XML_FILE, new TypeReference<>() {
        });
        persons.forEach(System.out::println);
        System.out.println("Persons read to persons.xml File");
    }


}
