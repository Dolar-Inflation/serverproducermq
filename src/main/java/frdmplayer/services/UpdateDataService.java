package frdmplayer.services;

import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.DTO.UpdateDto;
import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Employeephonerelation;
import frdmplayer.Entity.Phone;
import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.Repository.EmployesphoneRepository;
import frdmplayer.Repository.PhoneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateDataService {
   public final EmployeeRepository employeeRepository;
   public final EmployesphoneRepository emphoneRepository;
   public final PhoneRepository phoneRepository;

    public  void updateData(UpdateDto updateDto){
        Employee employee = employeeRepository.findById(updateDto.getEmployeeId()).orElseThrow(()
                ->new RuntimeException("Employee not found"));

        if (updateDto !=null) employee.setFio(updateDto.getFio());
        if (updateDto !=null) employee.setAddress(updateDto.getAddress());
        if (updateDto !=null) employee.setPosition(updateDto.getPosition());
        employeeRepository.save(employee);

        Phone phone = phoneRepository.findById(updateDto.getPhoneId()).orElseThrow(()
                ->new RuntimeException("Phone not found"));

        if (updateDto !=null) phone.setNumber(updateDto.getNumber());
        if (updateDto !=null) phone.setType(updateDto.getType());
        phoneRepository.save(phone);

        Employeephonerelation employeephonerelation = emphoneRepository.findById(updateDto.getId()).orElseThrow(()
                ->new RuntimeException("Employee phone relation not found"));
        if (updateDto !=null) employeephonerelation.setEmployee(employee);
        if (updateDto !=null) employeephonerelation.setPhone(phone);
        emphoneRepository.save(employeephonerelation);



    }
}
