package com.keepthinker.example.general.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
 
    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );
 
    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);


    @Mapping(source = "numberOfSeats", target = "seatCount")
    void copyCarToVo(Car car, @MappingTarget CarVo carVo);


}