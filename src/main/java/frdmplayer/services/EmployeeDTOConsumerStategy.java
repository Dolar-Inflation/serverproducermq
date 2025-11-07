package frdmplayer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeDTOConsumerStategy implements KafkaConsumerStrategy {
    private final SaveDataService saveDataService;
    private final DeleteDataById deleteDataById;
    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;

    @Override
    public boolean supports(Object obj, MethodsKafka operation) {
        return obj instanceof EmployeeDTO;
    }

    @Override
    public void handle(Object obj, MethodsKafka methodsKafka) {
        switch (methodsKafka){
            case CREATE -> saveDataService.saveEmployeDTO((EmployeeDTO) obj);
            case DELETE -> deleteDataById.deleteEmployeeDataById((EmployeeDTO) obj);
        }
    }

    @Override
    public Class getDTOClass(Object obj, MethodsKafka methodsKafka, String Classname) {
        switch (Classname){
            case "EmployeeDTO":
                EmployeeDTO employeeDTO = objectMapper.convertValue(obj, EmployeeDTO.class);
                System.out.println("Поток выполнения consumeEmployee: " + Thread.currentThread().getName());
                handle(employeeDTO, methodsKafka);

        }
    }


}

