package frdmplayer.services;

import frdmplayer.KafkaMethods.MethodsKafka;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaObertka<T> {
    private Object payload;
    private MethodsKafka methodsKafka;
    String payloadClassName;
}
