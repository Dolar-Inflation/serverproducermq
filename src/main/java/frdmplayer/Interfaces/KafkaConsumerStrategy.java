package frdmplayer.Interfaces;

import frdmplayer.KafkaMethods.MethodsKafka;

public interface KafkaConsumerStrategy {

    void handle(Object obj, MethodsKafka methodsKafka);
    String getClassName();

}
