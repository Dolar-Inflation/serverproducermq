package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Mapper;

import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employee;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employeephonerelation;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployePhoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RelationMapper {

    RelationMapper INSTANCE = Mappers.getMapper(RelationMapper.class);

    Employeephonerelation mapRelation(EmployePhoneDTO empPhoneDTO);

    EmployePhoneDTO mapRelation(Employeephonerelation employeephonerelation);
}
