package general.springboothomework.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private int id;
    private String firstName;
    private String lastName;

    private String cityName;
    private String address;

    private String passportSerialNumber;
    private String passportNumber;
}
