package frdmplayer.Interfaces;

import frdmplayer.KafkaMethods.MethodsKafka;

public interface KafkaConsumerStrategy {
    void handle(Object dto, MethodsKafka methodsKafka);
    Class getDTOClass();

}
