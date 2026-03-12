package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Mapper;

import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployePhoneFullDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployePhoneFullMapper {
    EmployePhoneFullMapper INSTANCE = Mappers.getMapper(EmployePhoneFullMapper.class);


}
