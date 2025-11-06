package frdmplayer.services;

import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Entity.Employeephonerelation;
import frdmplayer.MapEntity.EntityToDTO;
import frdmplayer.ObjToJSON.ObjToJSON;
import frdmplayer.Repository.EmployesphoneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor

public class GetDbInfo {

    public static Object getDbInfo;
    private final EmployesphoneRepository employesphoneRepository;
    private final EntityToDTO entityToDTO;
    private final ObjToJSON objToJSON;

    public List<EmployePhoneFullDTO>  getAllRelations(){
        return employesphoneRepository.findAll().stream()
                .map(entityToDTO::convertToEmployeePhoneFullDTO)
//                .map(ObjToJSON::convertToJson)
//                .forEach(objToJSON::convertToJson)
                .collect(Collectors.toList());



    }
    public List<EmployePhoneFullDTO>  getEmployeeById(Integer id){
       return employesphoneRepository.findById(id)
               .map(entityToDTO::convertToEmployeePhoneFullDTO)
               .stream().collect(Collectors.toList());
//               .orElseThrow(() -> new EntityNotFoundException("no id"));




    }

//    public Employeephonerelation getDbInfo(EmployePhoneDTO empPhoneDTO){
//////
////        Employee employee = employesphoneRepository.findAll(empPhoneDTO.getPhoneId(),empPhoneDTO.getEmployeId());
////            Employeephonerelation info = new Employeephonerelation();
////            info.setEmployee(employee);
////            return info;
////
//  }

}

