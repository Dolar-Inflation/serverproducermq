package frdmplayer.CrudTemplate;

import frdmplayer.Abstracts.CrudAbstractMethods;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Employeephonerelation;
import frdmplayer.Entity.Phone;
import frdmplayer.Interfaces.DtoMappinStrategy;
import frdmplayer.Repository.EmployesphoneRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class RelationCrudTemplate extends CrudAbstractMethods<Employeephonerelation, EmployePhoneDTO> {
    private final EmployesphoneRepository employesphoneRepository;
    private final DtoMappinStrategy<Employeephonerelation, EmployePhoneDTO> dtoMappinStrategy;


    public RelationCrudTemplate(EmployesphoneRepository employesphoneRepository, DtoMappinStrategy<Employeephonerelation, EmployePhoneDTO> dtoMappinStrategy) {
        super(dtoMappinStrategy);
        this.employesphoneRepository = employesphoneRepository;
        this.dtoMappinStrategy = dtoMappinStrategy;
    }

    @Override
    protected boolean existsId(Integer id) {
        return employesphoneRepository.existsById(id);
    }

    @Override
    protected void performDelete(Integer id) {
       employesphoneRepository.deleteById(id);
       System.out.println("Deleted " + id);

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
    protected Employeephonerelation performPatch(Integer id, EmployePhoneDTO dto) {
        Employeephonerelation employeephonerelation = employesphoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relation not found"));
                if(dto.getEmployeId() != null) {
                    Employee employee = new Employee();
                    employee.setId(dto.getEmployeId());
                    employeephonerelation.setEmployee(employee);
                }
                if(dto.getPhoneId() !=null) {
                    Phone phone = new Phone();
                    phone.setId(dto.getPhoneId());
                    employeephonerelation.setPhone(phone);
                }

        return employesphoneRepository.save(employeephonerelation);
    }


}
