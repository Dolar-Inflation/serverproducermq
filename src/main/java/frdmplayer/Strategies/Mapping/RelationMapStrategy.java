package frdmplayer.Strategies.Mapping;

import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Employeephonerelation;
import frdmplayer.Entity.Phone;
import frdmplayer.Interfaces.DtoMappinStrategy;
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
