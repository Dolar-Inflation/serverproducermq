//package frdmplayer.services;
//
//
//import frdmplayer.DTO.*;
//import frdmplayer.ObjToJSON.ObjToJSON;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaHandler;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.CompletableFuture;
//
//
//@Service
//@KafkaListener(topics = "test-topic", groupId = "my-group")
//public class KafkaConsumer {
//
//    private final SaveDataService saveDataService;
//    private final ObjToJSON objToJSON;
//    private final DeleteDataById deleteDataById;
//    private final UpdateDataService updateDataService;
//    @Autowired
//    public KafkaConsumer(SaveDataService saveDataService, ObjToJSON objToJSON, DeleteDataById deleteDataById, UpdateDataService updateDataService) {
//        this.saveDataService = saveDataService;
//        this.objToJSON = objToJSON;
//        this.deleteDataById = deleteDataById;
//        this.updateDataService = updateDataService;
//    }
//    @Async
//    @KafkaHandler
//    public CompletableFuture <Void> consumeMessage( EmployeeDTO message)  {
//
//        saveDataService.saveEmployeDTO(message);
////        objToJSON.convertToJson(message);
//
////        ObjectMapper mapper = new ObjectMapper();
////        String json = mapper.writeValueAsString(message);
////        System.out.println("Получено сообщение из Kafka: " + json);
//        System.out.println("Поток выполнения consumemessage: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//    }
//
//    @KafkaHandler
//    @Async
//    public CompletableFuture<Void> consumePhoneMessage(PhoneNumberDTO message)  {
//        saveDataService.savePhoneNumberDTO(message);
//
////        ObjectMapper mapper = new ObjectMapper();
////        String json = mapper.writeValueAsString(message);
////        System.out.println("Получен номер из Kafka: " + json);
//        System.out.println("Поток выполнения consumephonemessage: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//    }
//
//
//    @KafkaHandler
//    @Async
//    public CompletableFuture <Void> consumeRelation(EmployePhoneDTO message) {
//        saveDataService.saveEmployePhoneRelation(message);
//
////        ObjectMapper mapper = new ObjectMapper();
////        String json = mapper.writeValueAsString(message);
////        System.out.println("ПРИНЯТА СВЯЗЬ СО СТОРОНЫ КОНЬСЮМЕРА " + json);
//        System.out.println("exec thread of consumerelation: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//    }
//    @KafkaHandler
//    @Async
//    public CompletableFuture <Void> consumeDeleteRelationById(EmployePhoneFullDTO id){
//        deleteDataById.deleteRelationDataById(id);
//        return CompletableFuture.completedFuture(null);
//    }
////    @KafkaHandler
////    @Async
////    public CompletableFuture <Void> consumeDeleteEmployeeById( EmployeeDTO id,@Header("method") String header ){
////        System.out.println("header: " + header);
////        deleteDataById.deleteEmployeeDataById(id);
////        System.out.println("exec thread of deleteemployeebyid: " + Thread.currentThread().getName());
////        return CompletableFuture.completedFuture(null);
////    }
//    @KafkaHandler
//    @Async
//    public CompletableFuture <Void> UpdateByFULLDTO(UpdateDto message) {
//        updateDataService.updateData(message);
//        System.out.println("exec thread of update method: " + Thread.currentThread().getName());
//        return CompletableFuture.completedFuture(null);
//    }
//
//    @Async
//    @KafkaHandler(isDefault = true)
//    public CompletableFuture<Void> unknown(Object object) {
//        System.out.println("Неизвестный тип сообщения: " + object);
//        return CompletableFuture.completedFuture(null);
//    }
//}
