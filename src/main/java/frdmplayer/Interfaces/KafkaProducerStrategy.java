package frdmplayer.Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.KafkaMethods.MethodsKafka;

public interface KafkaProducerStrategy {

    boolean supports(Object obj, MethodsKafka operation);
    void send(/*String topic,*/Object dto) throws JsonProcessingException;



}
