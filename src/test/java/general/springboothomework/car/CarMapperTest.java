package general.springboothomework.car;

import org.junit.jupiter.api.Test;

import static general.springboothomework.car.CarMapper.carMapper;

class CarMapperTest {


    @Test
    void toCar() {
        CarDTO carDTO = new CarDTO("Chevrolet", "Malibu", 2020);
        Car car = carMapper.toCar(carDTO);
        System.out.println("car = " + car);
    }

    @Test
    void toCarDTO() {
        var car = new Car(null, "Chevrolet", "Spark", 2010);
        CarDTO carDTO = carMapper.toCarDTO(car);
        System.out.println("carDTO = " + carDTO);
    }
}