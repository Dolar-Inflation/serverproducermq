package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.Strategies.Mapping;//package frdmplayer.Strategies.Mapping;


import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employee;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employeephonerelation;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Phone;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployePhoneDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.DtoMappinStrategy;
import org.springframework.stereotype.Component;

@Component
public class RelationMapStrategy implements DtoMappinStrategy<Employeephonerelation, EmployePhoneDTO> {



    @Override
    public EmployePhoneDTO toDto(Employeephonerelation entity){
        EmployePhoneDTO dto = new EmployePhoneDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setPhoneId(entity.getPhone().getId());
        return dto;

    }
    @Override
    public Employeephonerelation toEntity(EmployePhoneDTO dto){
        Employeephonerelation entity = new Employeephonerelation();
        entity.setId(dto.getId());

        Employee employee = new Employee();
        employee.setId(dto.getEmployeId());
        entity.setEmployee(employee);

        Phone phone = new Phone();
        phone.setId(dto.getPhoneId());
        entity.setPhone(phone);

        return entity;

    }
    @Override
    public boolean supports(Class<?> entityClass) {
        return Employeephonerelation.class.equals(entityClass);
    }
    @Override
    public Class<Employeephonerelation> getEntityClass() {
        return Employeephonerelation.class;
    }
    @Override
    public Class<EmployePhoneDTO> getDtoClass() {
        return EmployePhoneDTO.class;
    }
}
