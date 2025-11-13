package frdmplayer.Strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.Interfaces.KafkaProducerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeDTOProducerStrategy implements KafkaProducerStrategy {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjToJSON objToJSON;
    private static final String TOPIC = "test-topic";
//
//
//    @Override
//    public boolean supports(Object dto, MethodsKafka method) {
//
//        return dto instanceof EmployeeDTO;
//    }
//    @Override
//    public void send(EmployeeDTO employeeDTO) throws JsonProcessingException {
//        objToJSON.convertToJson(employeeDTO);
//        kafkaTemplate.send(TOPIC, employeeDTO);
//        System.out.println("Sent EmployeeDTO with method " + employeeDTO);
//
//
//
//        }
//    }
        @Override
    public boolean supports(Object obj,MethodsKafka methodsKafka) {
            return obj instanceof EmployeeDTO;
        }
        @Override
        public void send(Object dto) throws JsonProcessingException {
            objToJSON.convertToJson(dto);
            kafkaTemplate.send(TOPIC,dto);
            System.out.println(dto);

        }
}




