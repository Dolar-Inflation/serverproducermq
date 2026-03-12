package com.messenger.consumer.DALBuisnessLogic.Consumer.Services;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.messenger.consumer.DALBuisnessLogic.Consumer.Config.MultiThreadConfig;

import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.KafkaObertka;
import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;

import com.messenger.consumer.DALBuisnessLogic.Consumer.Utility.ObjToJSON.ObjToJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.concurrent.CompletableFuture;

@Service
@KafkaListener(topics = "my-topic", groupId = "my-group")
public class Consumer {

    private final ObjToJSON objToJSON;


    private final Consume consume;
    private final MultiThreadConfig multiThreadConfig;
    public Object consumedPayload;

    @Autowired
    public Consumer( ObjToJSON objToJSON, Consume consume, MultiThreadConfig multiThreadConfig) {


        this.objToJSON = objToJSON;


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
        });

    }
    public Object getConsumed() {
        return consumedPayload;
    }
}

