package frdmplayer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.Interfaces.KafkaProducerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

@Service
@RequiredArgsConstructor
public class Producer {

@Autowired
    private final List<KafkaProducerStrategy> strategies;
    private final ExecutorService executorService;
    private final Semaphore semaphore;



    public Future send(Object dto , MethodsKafka methodsKafka)  {

        String payloadClassName = dto.getClass().getSimpleName();
        for(KafkaProducerStrategy strategy : strategies) {// прогон списка стратегий на поодержку отправки нужного dto
            if(strategy.supports(dto, methodsKafka)) {

                System.out.println(Thread.currentThread().getName() + ": Sent to Kafka");
                return executorService.submit(() -> {
                    try {

                        semaphore.acquire();
                        try {
                            sendWithStrategy(strategy, dto, methodsKafka, payloadClassName);
                            Thread.sleep(2000);
                            if (semaphore.availablePermits() <= 0) {
                                System.out.println(Thread.currentThread().getName() + " вошёл в критическую секцию");
                            }

                        }finally {
                            semaphore.release();
                            if (semaphore.availablePermits() > 0) {
                                System.out.println(Thread.currentThread().getName() + " вышел из неё");
                            }
                        }
                    } catch (JsonProcessingException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": Отправил : " + dto);
                    System.out.println(dto.getClass());
                });
            }
        }
        throw new RuntimeException("No support for KafkaProducerStrategy");
    }
    @SuppressWarnings("unchecked")
    public void sendWithStrategy(KafkaProducerStrategy strategy, Object dto, MethodsKafka methodsKafka,String payloadClassName) throws JsonProcessingException {
        //метод оборачивает дто crud методы и имя обьекта
        KafkaObertka obertka = new KafkaObertka(dto,methodsKafka,payloadClassName);
        strategy.send(obertka); // стратегия нужна здесь для реализации метода send
    }


}

