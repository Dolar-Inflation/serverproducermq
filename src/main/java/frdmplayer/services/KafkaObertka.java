package frdmplayer.services;

import frdmplayer.KafkaMethods.MethodsKafka;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaObertka {
    private Object payload;
    private MethodsKafka methodsKafka;
    String payloadClassName;
}
