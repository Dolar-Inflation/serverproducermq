package frdmplayer.Strategies.Producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.Interfaces.KafkaProducerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployePhoneFullDTOProducerStrategy implements KafkaProducerStrategy {
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final ObjToJSON objToJSON;
    private static final String TOPIC = "test-topic";

    @Override
    public boolean supports(Object dto, MethodsKafka methodsKafka) {
        return dto instanceof EmployePhoneFullDTO;
    }

    @Override
    public void send(Object dto) throws JsonProcessingException {
        objToJSON.convertToJson(dto);
        kafkaTemplate.send(TOPIC,dto);
        System.out.println("Sent " + dto + " to topic " + TOPIC );
    }

}
