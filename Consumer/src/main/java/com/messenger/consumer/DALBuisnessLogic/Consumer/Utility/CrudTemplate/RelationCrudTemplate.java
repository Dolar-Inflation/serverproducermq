package com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.CrudTemplate;


import com.messenger.consumer.DALBuisnessLogic.Consumer.Abstracts.CrudAbstractMethods;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employee;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Employeephonerelation;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Entity.Phone;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DAO.Repository.EmployesphoneRepository;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployePhoneDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.DtoMappinStrategy;
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
