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
                .map(entityToDTO::convertToEmployeePhoneFullDTO)
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

//    public List<EmployePhoneFullDTO>  getEmployeeById(Integer id){
////         employesphoneRepository.findById(id).isPresent();
//            List<EmployePhoneFullDTO> result = employesphoneRepository.findById(id)
////            return employesphoneRepository.findById(id)
//                    .map(entityToDTO::convertToAllData)
//                    .stream().collect(Collectors.toList());
//            result.forEach(dto -> {
//                try {
//                    log.info("employee by id",objectMapper.writeValueAsString(dto));
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            return result;
//
//
//
//        }
public EmployePhoneFullDTO GetById(Integer id){
    List<EmployePhoneFullDTO> result = employesphoneRepository.findById(id).stream()
            .map(entityToDTO::MapDataThroughId)
            .collect(Collectors.toList());
    result.forEach(dto -> {
        try {
            System.out.println(objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    });
    return result.stream().findFirst().get();
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



