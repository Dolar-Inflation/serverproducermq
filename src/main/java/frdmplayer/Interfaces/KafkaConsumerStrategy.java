package frdmplayer.Interfaces;

import frdmplayer.KafkaMethods.MethodsKafka;

public interface KafkaConsumerStrategy {
    boolean supports(Object obj, MethodsKafka operation);
    void handle(Object obj, MethodsKafka methodsKafka);
    Class getDTOClass(Object obj, MethodsKafka methodsKafka,String Classname);

}
