package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Mapper;

import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employee;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Phone;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployeeDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.PhoneNumberDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePhoneFromDto(PhoneNumberDTO dto, @MappingTarget Phone entity);

    List<PhoneNumberDTO> mapPhoneNumberDTOList(List<Phone> phoneList);

    PhoneNumberDTO PhoneToPhoneNumberDTO(Phone phone);

    Phone PhoneDTOToPhone(PhoneNumberDTO phoneNumberDTO);
}
