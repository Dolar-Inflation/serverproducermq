package frdmplayer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.Interfaces.KafkaProducerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

@Service
@RequiredArgsConstructor
public class Producer {


    private final List<KafkaProducerStrategy> strategies;
    private final ExecutorService executorService;
    private final Semaphore semaphore;
    private static final Logger log = LoggerFactory.getLogger(Producer.class);


    public void send(Object dto , MethodsKafka methodsKafka)  {
        String payloadClassName = dto.getClass().getSimpleName();
        try {


            log.info("отправляю {}", payloadClassName);
            for (KafkaProducerStrategy strategy : strategies) {// прогон списка стратегий на поодержку отправки нужного dto
                if (strategy.supports(dto, methodsKafka)) {
                    log.debug("поддержка стратегии {} {}", strategy.getClass().getSimpleName(), payloadClassName);
                    System.out.println(Thread.currentThread().getName() + ": Sent to Kafka");
                    executorService.submit(() -> {
                        try {
                            log.debug("Thread {} -1 из семафоры", Thread.currentThread().getName());
                            semaphore.acquire();
                            log.debug("Thread {} сколько осталось на счётчике семафоры permits={}", Thread.currentThread().getName(), semaphore.availablePermits());
                            try {
                                sendWithStrategy(strategy, dto, methodsKafka, payloadClassName);
                                Thread.sleep(2000);
                                if (semaphore.availablePermits() <= 0) {
                                    System.out.println(Thread.currentThread().getName() + " вошёл в критическую секцию");
                                }

                            } finally {
                                semaphore.release();
                                if (semaphore.availablePermits() >= 1) {
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
        }catch (Exception e) {
            log.warn("не нашлось стратегии для payload {}",payloadClassName);
            throw new RuntimeException("нет подходящей стратегии ");
        }
    }
    public void sendWithStrategy(KafkaProducerStrategy strategy, Object dto, MethodsKafka methodsKafka,String payloadClassName) throws JsonProcessingException {
        //метод оборачивает дто crud методы и имя обьекта
        KafkaObertka obertka = new KafkaObertka(dto,methodsKafka,payloadClassName);
        strategy.send(obertka); // стратегия нужна здесь для реализации метода send
    }


}

