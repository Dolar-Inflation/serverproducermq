//package frdmplayer.services;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import frdmplayer.DTO.*;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.CompletableFuture;
//
//@Service
//public class KafkaProduser  {
//
//    private static final String TOPIC = "test-topic";
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//
////    private final KafkaTemplate<String, EmployeeDTO> kafkaTemplate;
////    private final KafkaTemplate<String, PhoneNumberDTO> kafkaTemplatephone;
////    private final KafkaTemplate<String,EmployePhoneDTO> kafkaTemplateRelation;
////    private final KafkaTemplate<String, EmployePhoneFullDTO> kafkaTemplateFullEmployePhoneDto;
////    private  final KafkaTemplate<String,UpdateDto> kafkaTemplateUpdate;
//    /*private final ObjToJSON objToJSON;*/
//    private final UpdateDataService updateDataService;
//
//    public KafkaProduser(KafkaTemplate<String,Object> kafkaTemplate/*KafkaTemplate<String, EmployeeDTO> kafkaTemplate, KafkaTemplate<String, PhoneNumberDTO> kafkaTemplatephone, KafkaTemplate<String, EmployePhoneDTO> kafkaTemplateRelation, KafkaTemplate<String, EmployePhoneFullDTO> kafkaTemplateFullEmployePhoneDto, KafkaTemplate<String, UpdateDto> kafkaTemplateUpdate,*/ /*ObjToJSON objToJSON*/, UpdateDataService updateDataService) {
//        this.kafkaTemplate = kafkaTemplate;
//
//
////        this.kafkaTemplatephone = kafkaTemplatephone;
////        this.kafkaTemplateRelation = kafkaTemplateRelation;
////        this.kafkaTemplateFullEmployePhoneDto = kafkaTemplateFullEmployePhoneDto;
////        this.kafkaTemplateUpdate = kafkaTemplateUpdate;
//       /* this.objToJSON = objToJSON;*/
//        this.updateDataService = updateDataService;
//    }
//
//    @Async
//    public CompletableFuture<Void> sendMessage(EmployeeDTO message) throws JsonProcessingException {
//        System.out.println("Отправка сообщения: " + message);
//
//
//        kafkaTemplate.send(TOPIC, message);
//        System.out.println("Поток выполнения sendmessage: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//    }
//    @Async
//    public CompletableFuture<Void> sendPhoneNumber(PhoneNumberDTO message) throws JsonProcessingException {
//        System.out.println("отправка телефона" + message);
//
//        kafkaTemplate.send(TOPIC, message);
//        System.out.println("Поток выполнения sendphonenumber: " + Thread.currentThread().getName());
//        return  CompletableFuture.completedFuture(null);
//    }
//    @Async
//    public CompletableFuture<Void> sendRelation(EmployePhoneDTO message) throws JsonProcessingException {
//        System.out.println("<UNK> <UNK>" + message);
//
//        kafkaTemplate.send(TOPIC, message);
//        System.out.println("поток выполнения sendrelation: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//
//    }
//    @Async
//    public CompletableFuture<Void> sendFullEmployePhoneDeleteByID(EmployePhoneFullDTOStrategy message) throws JsonProcessingException {
//        System.out.println("delete by id"+message);
//
//        kafkaTemplate.send(TOPIC, message);
//        System.out.println("поток выполнения Delete: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//
//
//    }
//    @Async
//    public CompletableFuture<Void> SendEmployeeDelete(EmployeeDTO message) throws JsonProcessingException {
//        System.out.println("delete by id"+message);
//
//
//        kafkaTemplate.send(TOPIC,message);
//        System.out.println("<UNK> <UNK> Delete: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//
//    }
//
//
//    @Async
//    public CompletableFuture<Void> sendUpdateDto(UpdateDto message) throws JsonProcessingException {
//        System.out.println("<UNK> <UNK>"+message);
//
//        kafkaTemplate.send(TOPIC, message);
//        System.out.println("<UNK> <UNK> Update: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//    }
//
//}
