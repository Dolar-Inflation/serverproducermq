package frdmplayer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@KafkaListener(topics = "test-topic", groupId = "my-group")
public class Consumer {
    private final SaveDataService saveDataService;
    private final DeleteDataById deleteDataById;
    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;

    @Autowired
    public Consumer(SaveDataService saveDataService, DeleteDataById deleteDataById, ObjToJSON objToJSON, ObjectMapper objectMapper) {
        this.saveDataService = saveDataService;
        this.deleteDataById = deleteDataById;
        this.objToJSON = objToJSON;
        this.objectMapper = objectMapper;
    }

    @Async
    @KafkaHandler
    CompletableFuture<Void> consumer(KafkaObertka obertka) throws JsonProcessingException {
        System.out.println("OBERTKA = " + (obertka == null ? "null" : obertka.getClass().getName()));
        String className = obertka.getPayloadClassName();
        Object payload = obertka.getPayload();
        MethodsKafka methodsKafka = obertka.getMethodsKafka();
        objToJSON.convertToJson(payload);
        System.out.println(payload + "ДАННЫЕ ПОЛУЧЕНЫ" + methodsKafka);
        System.out.println("payload class = " + (payload == null ? "null" : payload.getClass().getName()));
        System.out.println("o.getPayload() = " + payload);
        System.out.println("methodsKafka = " + obertka.getMethodsKafka());
        switch (className) {
            case "EmployeeDTO":
                EmployeeDTO employeeDTO = objectMapper.convertValue(payload, EmployeeDTO.class);
                System.out.println("Поток выполнения consumeEmployee: " + Thread.currentThread().getName());
                handleEmployeeDTO(employeeDTO, methodsKafka);
            case "PhoneNumberDTO":
                PhoneNumberDTO phoneNumberDTO = objectMapper.convertValue(payload, PhoneNumberDTO.class);
                System.out.println("<UNK> <UNK> consumePhoneNumber: " + Thread.currentThread().getName());
                handlePhoneDTO(phoneNumberDTO, methodsKafka);
        }


        return CompletableFuture.completedFuture(null);

//        try {
//            EmployeeDTO employeeDTO;
//            if (payload instanceof EmployeeDTO emplDTO) {
//                employeeDTO = emplDTO;
//                ObjectMapper objectMapper = new ObjectMapper();
//                employeeDTO = objectMapper.convertValue(payload, EmployeeDTO.class);
//                System.out.println("Поток выполнения consumeEmployee: " + Thread.currentThread().getName());
//                handleEmployeeDTO(employeeDTO, methodsKafka);
//
//            }
//            else if (payload instanceof PhoneNumberDTO phoneNumberDTO) {
//                phoneNumberDTO = phoneNumberDTO;
//            }
//            else  {
//                System.out.println("suck dickens: " + payload.getClass().getName());
//
//            }
//            PhoneNumberDTO phoneNumberDTO;
//            if (payload instanceof PhoneNumberDTO pnDTO) {
//                phoneNumberDTO = pnDTO;
//            }
//            else if (payload instanceof PhoneNumberDTO pnDTO) {
//                ObjectMapper objectMapper = new ObjectMapper();
//                phoneNumberDTO = objectMapper.convertValue(payload, PhoneNumberDTO.class);
//                System.out.println("Поток выполнения consumePhone: " + Thread.currentThread().getName());
//                handlePhoneDTO(phoneNumberDTO, methodsKafka);
//            }
//
//
//            if (employeeDTO == null) {
//                throw new IllegalArgumentException("Cannot map payload to EmployeeDTO");
//            }
//
//
//
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException(e);
//        }
//
//        return CompletableFuture.completedFuture(null);
//    }
    }
    public void handleEmployeeDTO(EmployeeDTO employeeDTO, MethodsKafka methodsKafka) {
        switch (methodsKafka) {
            case CREATE -> saveDataService.saveEmployeDTO(employeeDTO);
            case DELETE -> deleteDataById.deleteEmployeeDataById(employeeDTO);
            //TODO внедрить бизнес логику


        }


    }
    public void handlePhoneDTO (PhoneNumberDTO phoneNumberDTO, MethodsKafka methodsKafka){
        switch (methodsKafka) {
            case CREATE -> saveDataService.savePhoneNumberDTO(phoneNumberDTO);

        }
    }
}














//    @Async
//    @KafkaHandler
//    public CompletableFuture<Void> ConsumerOfEmployee( EmployeeDTO employeeDTO, @Header("method") String method) {
//        System.out.println("Получено сообщение: " + employeeDTO + ", method=" + method);
//        switch (method) {
//            case "CREATE":
//                saveDataService.saveEmployeDTO(employeeDTO);
//                System.out.println("Employee created");
//                System.out.println("Поток выполнения consumeEmployee: " + Thread.currentThread().getName());
//                break;
//                case "DELETEBYID":
//                    deleteDataById.deleteEmployeeDataById(employeeDTO);
//                    System.out.println("Employee deleted: " + Thread.currentThread().getName());
//                    break;
//        }
//
//        System.out.println("Поток выполнения consumeEmployee: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//    }


