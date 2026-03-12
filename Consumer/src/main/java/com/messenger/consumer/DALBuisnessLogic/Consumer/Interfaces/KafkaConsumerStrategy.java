package com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces;


import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;

public interface KafkaConsumerStrategy {

    void handle(Object obj, MethodsKafka methodsKafka);
    String getClassName();

}
