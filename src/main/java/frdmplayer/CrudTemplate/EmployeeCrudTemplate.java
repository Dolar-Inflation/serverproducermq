package frdmplayer.CrudTemplate;

import frdmplayer.Abstracts.CrudAbstractMethods;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Interfaces.DtoMappinStrategy;

import frdmplayer.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component


public class EmployeeCrudTemplate extends CrudAbstractMethods<Employee,EmployeeDTO> {
    private final EmployeeRepository employeeRepository;
    private final DtoMappinStrategy<Employee, EmployeeDTO> employeeMapStrategy;

    public EmployeeCrudTemplate(EmployeeRepository employeeRepository,
                                DtoMappinStrategy<Employee, EmployeeDTO> employeeMapStrategy) {
        super(employeeMapStrategy);
        this.employeeRepository = employeeRepository;
        this.employeeMapStrategy = employeeMapStrategy;
    }


    @Override
    protected boolean existsId(Integer id) {
        return employeeRepository.existsById(id);
    }

    @Override
    protected void performDelete(Integer id) {
        employeeRepository.deleteById(id);
        System.out.println("Deleted Employee with id " + id);

    }

    @Override
    protected List<Employee> performReadAll() {

        return employeeRepository.findAll();
//        EmployeeDTO employeeDTO = employeeRepository.findAll().stream().forEach(employeeDTO::);
//        employeeRepository.findAll().stream().forEach(entityToDTO::convertToEmployeDTO)
    }
    @Override
    protected Optional<Employee> performReadById(Integer id) {
        return employeeRepository.findById(id);
    }
    @Override
    protected Employee performCreate (Employee entity) {
        return employeeRepository.save(entity);
    }
    @Override
    protected Employee performPatch(Integer id, EmployeeDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
        if(dto.getFio() != null) {employee.setFio(dto.getFio());}
        if (dto.getAddress() != null) {employee.setAddress(dto.getAddress());}
        if (dto.getPosition() != null) {employee.setPosition(dto.getPosition());}

        return employeeRepository.save(employee);

    }
}

