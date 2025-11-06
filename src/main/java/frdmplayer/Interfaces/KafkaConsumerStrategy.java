package frdmplayer.Interfaces;

import frdmplayer.KafkaMethods.MethodsKafka;

public interface KafkaConsumerStrategy<T> {
    void handle(T dto, MethodsKafka methodsKafka);
    Class<T> getDTOClass();

}
