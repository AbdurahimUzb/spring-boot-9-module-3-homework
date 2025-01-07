package general.springboothomework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Random;

@Mapper
public interface PersonMapper {

    PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "id", expression = "java(randomNumber())")
    @Mapping(target = "firstName", source = "nameDTO.name")
    @Mapping(target = "lastName", source = "nameDTO.surname")
    @Mapping(target = "cityName", source = "cityDTO.nameCity")
    @Mapping(target = "address", source = "cityDTO.addressCity")
    @Mapping(target = "passportSerialNumber", source = "passportDTO.serial")
    @Mapping(target = "passportNumber", source = "passportDTO.number")
    Person toPerson(NameDTO nameDTO, CityDTO cityDTO, PassportDTO passportDTO);

    default Integer randomNumber() {
        return new Random().nextInt(1, 100);
    }
}
