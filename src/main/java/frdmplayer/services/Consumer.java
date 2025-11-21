package frdmplayer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.Configs.MultiThreadConfig;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.ObjToJSON.ObjToJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@KafkaListener(topics = "test-topic", groupId = "my-group")
public class Consumer {

    private final ObjToJSON objToJSON;
    private final ObjectMapper objectMapper;

    private final Consume consume;
    private final MultiThreadConfig multiThreadConfig;
    public Object consumedPayload;

    @Autowired
    public Consumer( ObjToJSON objToJSON, ObjectMapper objectMapper, Consume consume, MultiThreadConfig multiThreadConfig) {


        this.objToJSON = objToJSON;
        this.objectMapper = objectMapper;

        this.consume = consume;
        this.multiThreadConfig = multiThreadConfig;
    }


    @Async
    @KafkaHandler
    public CompletableFuture<Void> consumer(KafkaObertka obertka) throws JsonProcessingException {
        System.out.println("OBERTKA = " + (obertka == null ? "null" : obertka.getClass().getName()));
        String className = obertka.getPayloadClassName();
        Object payload = obertka.getPayload();
        this.consumedPayload = payload;
        MethodsKafka methodsKafka = obertka.getMethodsKafka();
        objToJSON.convertToJson(payload);
        System.out.println(payload + "ДАННЫЕ ПОЛУЧЕНЫ" + methodsKafka);
        System.out.println("payload class = " + (payload == null ? "null" : payload.getClass().getName()));
        System.out.println("o.getPayload() = " + payload);
        System.out.println("methodsKafka = " + obertka.getMethodsKafka());



        return CompletableFuture.runAsync(()->{
            consume.consume(payload, className, methodsKafka);
            System.out.println("Консьюмер сработал");
        }/*,multiThreadConfig.executorService()*/);

    }
    public Object getConsumed() {
        return consumedPayload;
    }
}

