package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Strategies.Mapping;//package frdmplayer.Strategies.Mapping;


import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employee;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployeeDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.DtoMappinStrategy;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Mapper.EmployeeMapper;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapStrategy implements DtoMappinStrategy<Employee, EmployeeDTO> {
    private final EmployeeMapper employeeMapper;

    public EmployeeMapStrategy(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }


    @Override
    public EmployeeDTO toDto(Employee entity) {
        return new EmployeeDTO(entity.getId(), entity.getFio(), entity.getAddress(),entity.getPosition());
    }

    @Override
    public Employee toEntity(EmployeeDTO dto) {
//        Employee employee = new Employee();
//        employee.setId(dto.getId());
//        employee.setFio(dto.getFio());
//        employee.setAddress(dto.getAddress());
//        employee.setPosition(dto.getPosition());
//        return employee;
        return employeeMapper.mapToEmployee(dto);
    }
    @Override
    public boolean supports(Class<?> entityClass) {
        return Employee.class.equals(entityClass);
    }
    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }
    @Override
    public Class<EmployeeDTO> getDtoClass() {
        return EmployeeDTO.class;
    }


}
