package general.springboothomework.properties.person;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;
    private String name;
    private Integer age;
}
