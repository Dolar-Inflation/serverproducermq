package frdmplayer.Strategies.Mapping;

import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Employeephonerelation;
import frdmplayer.Entity.Phone;
import frdmplayer.Interfaces.DtoMappinStrategy;
import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.Repository.PhoneRepository;
import org.springframework.stereotype.Component;

@Component
public class FullTableMapStrategy implements DtoMappinStrategy<Employeephonerelation, EmployePhoneFullDTO> {
    private final EmployeeRepository employeeRepository;
    private final PhoneRepository phoneRepository;

    public FullTableMapStrategy(EmployeeRepository employeeRepository, PhoneRepository phoneRepository) {
        this.employeeRepository = employeeRepository;
        this.phoneRepository = phoneRepository;
    }


    @Override
    public EmployePhoneFullDTO toDto(Employeephonerelation employeephonerelation) {
        Employee employee = employeephonerelation.getEmployee();
        Phone phone = employeephonerelation.getPhone();

        return new EmployePhoneFullDTO(
                employeephonerelation.getId(),
                employee.getId(),
                employee.getFio(),
                employee.getAddress(),
                employee.getPosition(),
                phone.getId(),
                phone.getNumber(),
                phone.getType()
        );

    }

    @Override
    public Employeephonerelation toEntity(EmployePhoneFullDTO employePhoneFullDTO) {
        Employee employee = employeeRepository.findById(employePhoneFullDTO.getEmployeId()).orElseThrow(()
                ->new RuntimeException("Employee not found"));

        employee.setFio(employePhoneFullDTO.getFio());
        employee.setAddress(employePhoneFullDTO.getAddress());
        employee.setPosition(employePhoneFullDTO.getPosition());


        Phone phone = phoneRepository.findById(employePhoneFullDTO.getPhoneId()).orElseThrow(()
                ->new RuntimeException("Phone not found"));

        phone.setNumber(employePhoneFullDTO.getNumber());
        phone.setType(employePhoneFullDTO.getType());


        Employeephonerelation relation = new Employeephonerelation();

        relation.setId(employePhoneFullDTO.getId());
        relation.setEmployee(employee);
        relation.setPhone(phone);
        return relation;
    }

    @Override
    public boolean supports(Class<?> dtoClass) {
        return EmployePhoneFullDTO.class.equals(dtoClass);
    }

    @Override
    public Class<Employeephonerelation> getEntityClass() {
        return Employeephonerelation.class;
    }

    @Override
    public Class<EmployePhoneFullDTO> getDtoClass() {
        return EmployePhoneFullDTO.class;
    }
}
