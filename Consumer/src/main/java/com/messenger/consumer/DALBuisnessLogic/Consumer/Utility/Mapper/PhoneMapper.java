package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Mapper;

import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Phone;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.PhoneNumberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    PhoneNumberDTO PhoneToPhoneNumberDTO(Phone phone);

    Phone PhoneDTOToPhone(PhoneNumberDTO phoneNumberDTO);
}
