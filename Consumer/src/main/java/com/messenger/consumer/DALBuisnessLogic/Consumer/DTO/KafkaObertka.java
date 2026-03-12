package com.messenger.consumer.DALBuisnessLogic.Consumer.DTO;


import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaObertka {
    private Object payload;
    private MethodsKafka methodsKafka;
    String payloadClassName;
}
