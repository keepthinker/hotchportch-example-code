package com.keepthinker.example.general.mapstruct;

import com.keepthinker.example.general.util.Utils;

public class MapStructMain {
    public static void main(String[] args) {
        Car car = new Car();
        car.setMake("Porsche");
        car.setType("Luxury");
        car.setNumberOfSeats(4);
        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
        System.out.println(Utils.toJsonString(carDto));

        CarVo carVo = new CarVo();
        CarMapper.INSTANCE.copyCarToVo(car, carVo);
        System.out.println(Utils.toJsonString(carVo));
    }
}
