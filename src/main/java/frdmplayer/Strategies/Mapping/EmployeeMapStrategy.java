package frdmplayer.Strategies.Mapping;

import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Interfaces.DtoMappinStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmployeeMapStrategy implements DtoMappinStrategy<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO toDto(Employee entity) {
        return new EmployeeDTO(entity.getId(), entity.getFio(), entity.getAddress(),entity.getPosition());
    }

    @Override
    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFio(dto.getFio());
        employee.setAddress(dto.getAddress());
        employee.setPosition(dto.getPosition());
        return employee;
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
