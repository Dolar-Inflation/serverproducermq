//package frdmplayer.services;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import frdmplayer.DTO.EmployePhoneDTO;
//import frdmplayer.Entity.Employeephonerelation;
//import frdmplayer.Interfaces.KafkaProducerStrategy;
//import frdmplayer.KafkaMethods.MethodsKafka;
//import frdmplayer.ObjToJSON.ObjToJSON;
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class RelationDTOProducerStrategy implements KafkaProducerStrategy<EmployePhoneDTO>{
//    private final KafkaTemplate<String,Object> kafkaTemplate;
//    private final ObjToJSON objToJSON;
//    private static final String TOPIC = "test-topic";
//
//    @Override
//    public boolean supports(Object dto, MethodsKafka method){
//        return dto instanceof EmployePhoneDTO;
//    }
//
//    @Override
//    public void send(KafkaObertka<EmployePhoneDTO> obertka ) throws JsonProcessingException {
//        objToJSON.convertToJson(obertka);
//        kafkaTemplate.send(TOPIC,obertka);
//        System.out.println("Sent " + obertka + " to topic " + TOPIC +"method"+ obertka.getMethodsKafka());
//
//    }
//
//
//}
