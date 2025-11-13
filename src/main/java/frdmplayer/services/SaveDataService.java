package frdmplayer.services;


import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Entity.*;
import frdmplayer.MapEntity.EntityToDTO;
import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.Repository.EmployesphoneRepository;
import frdmplayer.Repository.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveDataService {


    private final EmployeeRepository repository;
    private final PhoneRepository numberRepository;
    private final PhoneRepository phoneRepository;
    private final EmployesphoneRepository employesphoneRepository;
    private final EntityToDTO entityToDTO;

    @Autowired
    public SaveDataService(EmployeeRepository repository, PhoneRepository numberRepository, PhoneRepository phoneRepository, EmployeeRepository employeeRepository, EmployesphoneRepository sphoneRepository, EmployesphoneRepository employesphoneRepository, EntityToDTO entityToDTO) {
        this.repository = repository;
        this.numberRepository = numberRepository;
        this.phoneRepository = phoneRepository;
        this.employesphoneRepository = employesphoneRepository;
        this.entityToDTO = entityToDTO;


    }
    @Transactional
    public void saveEmployeDTO(EmployeeDTO employeeDTO){
        Employee employe = entityToDTO.convertToEmployeDTO(employeeDTO);
        repository.save(employe);
        System.out.println(repository.findById(employe.getId()));
    }

    public void savePhoneNumberDTO(PhoneNumberDTO phoneNumberDTO){
        Phone phonenumber = entityToDTO.convertToPhoneNumberDTO(phoneNumberDTO);
        numberRepository.save(phonenumber);
    }

    public void saveEmployePhoneRelation(EmployePhoneDTO employePhoneDTO){
        Employeephonerelation employesphone = entityToDTO.convertToEmployeePhoneDTO(employePhoneDTO);
        employesphoneRepository.save(employesphone);
    }
    



}
