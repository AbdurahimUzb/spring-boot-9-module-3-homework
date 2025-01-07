package general.springboothomework.car;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface CarMapper {

    @Mapping(target = "id", expression = "java(generateID())")
    Car toCar(CarDTO carDTO);

    CarDTO toCarDTO(Car car);

    default String generateID() {
        return UUID.randomUUID().toString();
    }

    CarMapper carMapper = Mappers.getMapper(CarMapper.class);
}
