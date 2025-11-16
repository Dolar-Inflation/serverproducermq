package frdmplayer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GetDbInfo {


    private final EmployesphoneRepository employesphoneRepository;
    private final EntityToDTO entityToDTO;
    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(GetDbInfo.class);

    public List<EmployePhoneFullDTO>  getAllRelations(){

        List<EmployePhoneFullDTO> result = employesphoneRepository.findAll().stream()
                .map(entityToDTO::convertToAllData)
//                .map(ObjToJSON::convertToJson)
//                .forEach(objToJSON::convertToJson)

                .collect(Collectors.toList());
        result.forEach(dto -> {
            try {
                log.info("DTO из БД: {}", objectMapper.writeValueAsString(dto));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
//        result.forEach(System.out::println);
        return result;





    }

    public List<EmployePhoneFullDTO>  getEmployeeById(Integer id){
        if (employesphoneRepository.findById(id).isPresent()) {
            return employesphoneRepository.findById(id)
                    .map(entityToDTO::convertToEmployeePhoneFullDTO)
                    .stream().collect(Collectors.toList());
        }
        else {
            throw new EntityNotFoundException("Employee id not found");
        }

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

