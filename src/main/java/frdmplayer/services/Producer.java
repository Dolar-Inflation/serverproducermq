package frdmplayer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.Interfaces.KafkaProducerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class Producer {


    private final List<KafkaProducerStrategy> strategies;


    @Async
    public CompletableFuture<Void>send(Object dto , MethodsKafka methodsKafka) throws JsonProcessingException {
        String payloadClassName = dto.getClass().getSimpleName();
        for(KafkaProducerStrategy strategy : strategies) {
            if(strategy.supports(dto, methodsKafka)) {
                sendWithStrategy(strategy,dto,methodsKafka,payloadClassName);
                System.out.println(Thread.currentThread().getName() + ": Sent to Kafka");
                return CompletableFuture.completedFuture(null);
            }
        }
        throw new RuntimeException("No support for KafkaProducerStrategy");
    }
    @SuppressWarnings("unchecked")
    public void sendWithStrategy(KafkaProducerStrategy strategy, Object dto, MethodsKafka methodsKafka,String payloadClassName) throws JsonProcessingException {

        KafkaObertka obertka = new KafkaObertka(dto,methodsKafka,payloadClassName);
        strategy.send(obertka);
    }


}

