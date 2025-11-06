package frdmplayer.Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.services.KafkaObertka;
import org.apache.kafka.common.internals.Topic;

public interface KafkaProducerStrategy<T> {

    boolean supports(Object obj, MethodsKafka operation);
    void send(/*String topic,*/Object dto) throws JsonProcessingException;



}
