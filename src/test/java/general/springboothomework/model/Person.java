package general.springboothomework.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@JsonPropertyOrder({"age", "lastName", "firstName"})
public class Person {

    private final String firstName;
    private final String lastName;
    private final Integer age;

}
