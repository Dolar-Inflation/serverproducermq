package frdmplayer.MapEntity;

import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Entity.*;
import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.Repository.EmployesphoneRepository;
import frdmplayer.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityToDTO {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployesphoneRepository emphoneRepository;
    @Autowired
    PhoneRepository phoneRepository;


    public Employee convertToEmployeDTO(EmployeeDTO employeeDTO) {
        Employee employe = new Employee();
//        employe.setId(employeDTO.getId());
        employe.setFio(employeeDTO.getFio());
        employe.setAddress(employeeDTO.getAddress());
        employe.setPosition(employeeDTO.getPosition());
        return employe;
    }

    public Phone convertToPhoneNumberDTO(PhoneNumberDTO phoneNumberDTO) {
        Phone phonenumber = new Phone();
        phonenumber.setNumber(phoneNumberDTO.getNumber());
        phonenumber.setType(phoneNumberDTO.getType());
        return phonenumber;
    }

    public Employeephonerelation convertToEmployeePhoneDTO(EmployePhoneDTO employePhoneDTO) {


        Employee employee = employeeRepository.findById(employePhoneDTO.getEmployeId()).orElse(null);

        Phone phone = phoneRepository.findById(employePhoneDTO.getPhoneId()).orElse(null);

        Employeephonerelation employeephonerelation = new Employeephonerelation();

        employeephonerelation.setEmployee(employee);
        employeephonerelation.setPhone(phone);
        return employeephonerelation;


//        Employee employee = employeeRepository.findById(employePhoneDTO.getEmployeId()).orElse(null);
//
//        Phone phonenumber = phoneRepository.findById(employePhoneDTO.getPhoneId()).orElse(null);
//
//
////        Employe employerel =  employeeRepository.findById(employePhoneDTO.getEmploye().getId()).orElse(null);
////
////        Phonenumber phonenumberrel= phoneNumberRepository.findById(employePhoneDTO.getPhoneid().getId()).orElse(null);
//
//
//
//        Employesphone employesphone = new Employesphone();
//
//        employesphone.setEmploye(employee);
//        employesphone.setPhoneid(phonenumber);
//        employesphone.setId(employePhoneDTO.getEmployeId());
//        return employesphone;

    }

    public EmployePhoneFullDTO convertToEmployeePhoneFullDTO(Employeephonerelation employeephonerelation) {
        EmployePhoneFullDTO employePhoneFullDTO = new EmployePhoneFullDTO();

        employePhoneFullDTO.setId(employeephonerelation.getId());

        Employee employee = employeephonerelation.getEmployee();

        employePhoneFullDTO.setEmployeId(employee.getId());
        employePhoneFullDTO.setFio(employee.getFio());
        employePhoneFullDTO.setAddress(employee.getAddress());
        employePhoneFullDTO.setPosition(employee.getPosition());

        Phone phone = employeephonerelation.getPhone();

        employePhoneFullDTO.setPhoneId(phone.getId());
        employePhoneFullDTO.setType(phone.getType());
        employePhoneFullDTO.setNumber(phone.getNumber());

        return employePhoneFullDTO;
    }
    public EmployePhoneFullDTO convertToAllData(Employeephonerelation employeephonerelation) {
        EmployePhoneFullDTO dto = new EmployePhoneFullDTO();

        // переносим id самой связи
        dto.setId(employeephonerelation.getId());

        Employee employee = employeephonerelation.getEmployee();
        if (employee != null) {
            dto.setEmployeId(employee.getId());
            dto.setFio(employee.getFio());
            dto.setAddress(employee.getAddress());
            dto.setPosition(employee.getPosition());
        }

        Phone phone = employeephonerelation.getPhone();
        if (phone != null) {
            dto.setPhoneId(phone.getId());
            dto.setType(phone.getType());
            dto.setNumber(phone.getNumber());
        }

        return dto;
    }
    public EmployePhoneFullDTO MapDataThroughId(Employeephonerelation employeephonerelation) {
        EmployePhoneFullDTO dto = new EmployePhoneFullDTO();
        dto.setId(employeephonerelation.getEmployee().getId());

        Employee employee = employeephonerelation.getEmployee();
        if (employee != null) {
            dto.setEmployeId(employee.getId());
            dto.setFio(employee.getFio());
            dto.setAddress(employee.getAddress());
            dto.setPosition(employee.getPosition());
        }

        Phone phone = employeephonerelation.getPhone();
        if (phone != null) {
            dto.setPhoneId(phone.getId());
            dto.setType(phone.getType());
            dto.setNumber(phone.getNumber());
        }
        return dto;

    }
}



