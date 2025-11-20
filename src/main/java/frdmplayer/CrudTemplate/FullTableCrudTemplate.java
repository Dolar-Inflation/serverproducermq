package frdmplayer.Strategies.Mapping;


import frdmplayer.Abstracts.CrudAbstractMethods;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Employeephonerelation;
import frdmplayer.Entity.Phone;
import frdmplayer.Interfaces.DtoMappinStrategy;
import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.Repository.EmployesphoneRepository;
import frdmplayer.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class FullTableCrudTemplate extends CrudAbstractMethods<Employeephonerelation,EmployePhoneFullDTO> {
    @Autowired
    private final EmployesphoneRepository employesphoneRepository;
    private final EmployeeRepository employeeRepository;
    private final PhoneRepository phoneRepository;
    private final DtoMappinStrategy<Employeephonerelation,EmployePhoneFullDTO> dtoMappinStrategy;

    public FullTableCrudTemplate(EmployesphoneRepository employesphoneRepository, EmployeeRepository employeeRepository, PhoneRepository phoneRepository, DtoMappinStrategy<Employeephonerelation, EmployePhoneFullDTO> dtoMappinStrategy) {
        super(dtoMappinStrategy);
        this.employesphoneRepository = employesphoneRepository;
        this.employeeRepository = employeeRepository;
        this.phoneRepository = phoneRepository;
        this.dtoMappinStrategy = dtoMappinStrategy;
    }


    @Override
    protected boolean existsId(Integer id) {
        return employesphoneRepository.existsById(id);
    }

    @Override
    protected void performDelete(Integer id) {
        employesphoneRepository.deleteById(id);

    }

    @Override
    protected List<Employeephonerelation> performReadAll() {
        return employesphoneRepository.findAll();
    }

    @Override
    protected Optional<Employeephonerelation> performReadById(Integer id) {
        return employesphoneRepository.findById(id);
    }

    @Override
    protected Employeephonerelation performCreate(Employeephonerelation entity) {
        return employesphoneRepository.save(entity);
    }

    @Override
    protected Employeephonerelation performPatch(Integer id, EmployePhoneFullDTO dto) {
//        Employeephonerelation relation = mappingStrategy.toEntity(dto);
//        return employesphoneRepository.save(relation);

        Employeephonerelation relation = employesphoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relation not found"));


        Employee employee = relation.getEmployee();
        employee.setFio(dto.getFio());
        employee.setAddress(dto.getAddress());
        employee.setPosition(dto.getPosition());
        employeeRepository.save(employee);


        Phone phone = relation.getPhone();
        phone.setNumber(dto.getNumber());
        phone.setType(dto.getType());
        phoneRepository.save(phone);


        relation.setEmployee(employee);
        relation.setPhone(phone);

        return employesphoneRepository.save(relation);
    }



}