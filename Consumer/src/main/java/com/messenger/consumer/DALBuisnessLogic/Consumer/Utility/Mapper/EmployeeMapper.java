package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Mapper;

import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employee;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployeeDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
//    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployeeFromDto(EmployeeDTO dto, @MappingTarget Employee entity);

    Employee mapToEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO mapToEmployeeDTO(Employee employee);

    List<EmployeeDTO> mapToEmployeeDTOList(List<Employee> employees);
}
