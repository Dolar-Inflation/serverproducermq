package frdmplayer.Strategies.Producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Interfaces.KafkaProducerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneDTOProducerStrategy implements KafkaProducerStrategy {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "test-topic";
    private final ObjToJSON objToJSON;


    @Override
    public boolean supports(Object obj,MethodsKafka methodsKafka) {
        return obj instanceof PhoneNumberDTO;
    }
    @Override
    public void send(Object dto) throws JsonProcessingException {
        objToJSON.convertToJson(dto);
        kafkaTemplate.send(TOPIC,dto);
        System.out.println(dto);
    }
}


