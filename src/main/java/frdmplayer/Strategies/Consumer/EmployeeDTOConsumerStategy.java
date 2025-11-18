package frdmplayer.Strategies.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.CrudTemplate.EmployeeCrudTemplate;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;


import frdmplayer.services.UpdateDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeDTOConsumerStategy implements KafkaConsumerStrategy {


    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;
    private final EmployeeCrudTemplate employeeCrudTemplate;



    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
//            case CREATE -> saveDataService.saveEmployeDTO((EmployeeDTO) obj);
            case CREATE -> employeeCrudTemplate.create((EmployeeDTO) obj);
            case DELETE -> employeeCrudTemplate.deleteById(((EmployeeDTO) obj).getId());
            case READALL -> {
                List<EmployeeDTO> employeeDTOList= employeeCrudTemplate.readAll();
                employeeDTOList.forEach(System.out::println);
            }
            case READ -> {
                EmployeeDTO employeeDTO = employeeCrudTemplate.readById(((EmployeeDTO) obj).getId());
            System.out.println(employeeDTO);
            }
            case PATCH -> {
                EmployeeDTO employeeDTO = (EmployeeDTO) obj;
                employeeCrudTemplate.patch(employeeDTO.getId(), employeeDTO);
            }
        }
    }

    @Override
    public String getClassName() {
        return EmployeeDTO.class.getSimpleName();
    }


}




