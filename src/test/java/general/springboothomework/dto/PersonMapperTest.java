package general.springboothomework.dto;

import org.junit.jupiter.api.Test;

import static general.springboothomework.dto.PersonMapper.personMapper;
import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    @Test
    void toPerson() {

        NameDTO nameDTO = new NameDTO("John", "Doe");
        CityDTO cityDTO = new CityDTO("San Francisco", "CA");
        PassportDTO passportDTO = new PassportDTO("AB", "1234567");

        Person person = personMapper.toPerson(nameDTO, cityDTO, passportDTO);
        System.out.println(person);
    }
}