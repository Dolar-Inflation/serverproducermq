package frdmplayer.services;

import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.MapEntity.EntityToDTO;
import frdmplayer.Repository.EmployeeRepository;
import frdmplayer.Repository.EmployesphoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteDataById {
    EmployeeRepository employeeRepository;
    EmployesphoneRepository employesphoneRepository;
    EntityToDTO entityToDTO;


    public void deleteRelationDataById(EmployePhoneDTO employePhoneDTO) {
        employesphoneRepository.deleteById(employePhoneDTO.getId());

    }
    public void deleteEmployeeDataById(EmployeeDTO employeeDTO) {
        employeeRepository.deleteById(employeeDTO.getId());
        System.out.println("Employee deleted successfully" + employeeDTO);
    }

    public void deleteEmployeePhoneDataById(PhoneNumberDTO phoneNumberDTO) {
        employeeRepository.deleteById(phoneNumberDTO.getId());
    }

}
