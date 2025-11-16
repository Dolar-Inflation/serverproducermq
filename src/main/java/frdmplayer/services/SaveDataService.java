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
    private final LockTableService lockTableService;

    @Autowired
    public SaveDataService(EmployeeRepository repository, PhoneRepository numberRepository, PhoneRepository phoneRepository,  EmployesphoneRepository employesphoneRepository, EntityToDTO entityToDTO, LockTableService lockTableService) {
        this.repository = repository;
        this.numberRepository = numberRepository;
        this.phoneRepository = phoneRepository;
        this.employesphoneRepository = employesphoneRepository;
        this.entityToDTO = entityToDTO;


        this.lockTableService = lockTableService;
    }
    @Transactional
    public void saveEmployeDTO(EmployeeDTO employeeDTO){
        lockTableService.lockTable("employee");
        try {
            Employee employe = entityToDTO.convertToEmployeDTO(employeeDTO);
            repository.save(employe);
            repository.flush();
            System.out.println("Saved employee with ID: " + repository.findById(employe.getId()));
            System.out.println(repository.findById(employe.getId()));
        } finally {
            lockTableService.unlockTable("employee");
        }
    }

    public void savePhoneNumberDTO(PhoneNumberDTO phoneNumberDTO){
        lockTableService.lockTable("phone");
        try {


            Phone phonenumber = entityToDTO.convertToPhoneNumberDTO(phoneNumberDTO);
            numberRepository.save(phonenumber);
            numberRepository.flush();
        }finally {
            lockTableService.unlockTable("phone");
        }
    }

    public void saveEmployePhoneRelation(EmployePhoneDTO employePhoneDTO){
        lockTableService.lockTable("employeephonerelation");
        try {


            Employeephonerelation employesphone = entityToDTO.convertToEmployeePhoneDTO(employePhoneDTO);
            employesphoneRepository.save(employesphone);
        }finally {
            lockTableService.unlockTable("employeephonerelation");
        }


    }
    



}
