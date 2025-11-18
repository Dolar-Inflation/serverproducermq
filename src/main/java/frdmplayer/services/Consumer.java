package frdmplayer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.Configs.MultiThreadConfig;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.DTO.EmployePhoneFullDTO;
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
import java.util.concurrent.ExecutorService;

@Service
@KafkaListener(topics = "test-topic", groupId = "my-group")
public class Consumer {

    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;
    private final UpdateDataService updateDataService;
    private final Consume consume;
    private final MultiThreadConfig multiThreadConfig;

    @Autowired
    public Consumer( ObjToJSON objToJSON, ObjectMapper objectMapper, UpdateDataService updateDataService, Consume consume, MultiThreadConfig multiThreadConfig) {


        this.objToJSON = objToJSON;
        this.objectMapper = objectMapper;
        this.updateDataService = updateDataService;
        this.consume = consume;
        this.multiThreadConfig = multiThreadConfig;
    }


//    @Async
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



        return CompletableFuture.runAsync(()->{
            consume.consume(payload, className, methodsKafka);
            System.out.println("Консьюмер сработал");
        },multiThreadConfig.executorService());
    }
}

//        switch (className) {
//            case "EmployeeDTO":
//                EmployeeDTO employeeDTO = objectMapper.convertValue(payload, EmployeeDTO.class);
//                System.out.println("Поток выполнения consumeEmployee: " + Thread.currentThread().getName());
//
////                handleEmployeeDTO(employeeDTO, methodsKafka);
//            case "PhoneNumberDTO":
//                PhoneNumberDTO phoneNumberDTO = objectMapper.convertValue(payload, PhoneNumberDTO.class);
//                System.out.println("<UNK> <UNK> consumePhoneNumber: " + Thread.currentThread().getName());
//                handlePhoneDTO(phoneNumberDTO, methodsKafka);
//                case "EmployePhoneDTO":
//                    EmployePhoneDTO empPhoneDTO = objectMapper.convertValue(payload, EmployePhoneDTO.class);
//                    System.out.println("<UNK> <UNK> consumeEmployeePhone: " + Thread.currentThread().getName());
//                    handleEmployePhoneDTO(empPhoneDTO, methodsKafka);
//            case "EmployePhoneFullDTO":
//                EmployePhoneFullDTO employePhoneFullDTO=objectMapper.convertValue(payload, EmployePhoneFullDTO.class);
//                System.out.println("<UNK> <UNK> consumeEmployeePhoneFull: " + Thread.currentThread().getName());
//                handleEmployePhoneFullDTO(employePhoneFullDTO, methodsKafka);
//
//        }




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
//    }
//    public void handleEmployeeDTO(EmployeeDTO employeeDTO, MethodsKafka methodsKafka) {
//        switch (methodsKafka) {
//            case CREATE -> saveDataService.saveEmployeDTO(employeeDTO);
//            case DELETE -> deleteDataById.deleteEmployeeDataById(employeeDTO);
//
//
//
//        }
//
//
//    }
//    public void handlePhoneDTO (PhoneNumberDTO phoneNumberDTO, MethodsKafka methodsKafka){
//        switch (methodsKafka) {
//            case CREATE -> saveDataService.savePhoneNumberDTO(phoneNumberDTO);
//            case DELETE -> deleteDataById.deleteEmployeePhoneDataById(phoneNumberDTO);
//
//        }
//    }
//    public void handleEmployePhoneDTO(EmployePhoneDTO employePhoneDTO,MethodsKafka methodsKafka){
//        switch (methodsKafka) {
//            case CREATE -> saveDataService.saveEmployePhoneRelation(employePhoneDTO);
//            case DELETE -> deleteDataById.deleteRelationDataById(employePhoneDTO);
//
//        }
//
//
//    }
//    public void handleEmployePhoneFullDTO(EmployePhoneFullDTO employePhoneFullDTO,MethodsKafka methodsKafka ){
//        switch (methodsKafka) {
//            case PATCH -> updateDataService.updateData(employePhoneFullDTO);
//        }
//    }
//}














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


