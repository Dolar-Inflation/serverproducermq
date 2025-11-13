package frdmplayer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.Interfaces.KafkaProducerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class Producer {


    private final List<KafkaProducerStrategy> strategies;
    private final ExecutorService executorService;
    private final ObjToJSON objToJSON;


//    @Async
    public CompletableFuture<Void>send(Object dto , MethodsKafka methodsKafka) throws JsonProcessingException {

        String payloadClassName = dto.getClass().getSimpleName();
        for(KafkaProducerStrategy strategy : strategies) {
            if(strategy.supports(dto, methodsKafka)) {

                System.out.println(Thread.currentThread().getName() + ": Sent to Kafka");
                return CompletableFuture.runAsync(()->{
                    try {

                        sendWithStrategy(strategy,dto,methodsKafka,payloadClassName);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": Отправил : " + dto);
                    System.out.println(dto.getClass());
                },executorService);
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

