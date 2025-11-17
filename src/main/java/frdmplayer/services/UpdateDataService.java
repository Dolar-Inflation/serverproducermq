package frdmplayer.services;

import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Employeephonerelation;
import frdmplayer.Entity.Phone;
import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.Repository.EmployesphoneRepository;
import frdmplayer.Repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateDataService {
   public final EmployeeRepository employeeRepository;
   public final EmployesphoneRepository emphoneRepository;
   public final PhoneRepository phoneRepository;

    public  void updateData(EmployePhoneFullDTO employePhoneFullDTO){
        Employee employee = employeeRepository.findById(employePhoneFullDTO.getEmployeId()).orElseThrow(()
                ->new RuntimeException("Employee not found"));

        if (employePhoneFullDTO !=null) employee.setFio(employePhoneFullDTO.getFio());
        if (employePhoneFullDTO !=null) employee.setAddress(employePhoneFullDTO.getAddress());
        if (employePhoneFullDTO !=null) employee.setPosition(employePhoneFullDTO.getPosition());
        employeeRepository.save(employee);

        Phone phone = phoneRepository.findById(employePhoneFullDTO.getPhoneId()).orElseThrow(()
                ->new RuntimeException("Phone not found"));

        if (employePhoneFullDTO !=null) phone.setNumber(employePhoneFullDTO.getNumber());
        if (employePhoneFullDTO !=null) phone.setType(employePhoneFullDTO.getType());
        phoneRepository.save(phone);

        Employeephonerelation employeephonerelation = emphoneRepository.findById(employePhoneFullDTO.getId()).orElseThrow(()
                ->new RuntimeException("Employee phone relation not found"));
        if (employePhoneFullDTO !=null) employeephonerelation.setEmployee(employee);
        if (employePhoneFullDTO !=null) employeephonerelation.setPhone(phone);
        emphoneRepository.save(employeephonerelation);



    }
}
