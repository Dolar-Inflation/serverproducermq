package com.messenger.consumer.DALBuisnessLogic.Consumer.Services;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployePhoneDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployePhoneFullDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.EmployeeDTO;
import com.messenger.consumer.DALBuisnessLogic.Consumer.DTO.PhoneNumberDTO;

import com.messenger.consumer.DALBuisnessLogic.Consumer.Interfaces.KafkaConsumerStrategy;

import com.messenger.consumer.DALBuisnessLogic.Consumer.KafkaMethods.MethodsKafka;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class Consume {
    private static final Logger log = LoggerFactory.getLogger(Consume.class);
    private final List<KafkaConsumerStrategy> strategies;
    private final ObjectMapper mapper;



    @Async
    public CompletableFuture<Void> consume(Object obj,String objClassName, MethodsKafka methodsKafka) {

        for (KafkaConsumerStrategy strategy : strategies) {
            if(strategy.getClassName().equals(objClassName)) {
                Class<?> targetClass = getClassByName(objClassName);
                log.debug("получен обьект {}",targetClass.getName());
                Object typedPayload = mapper.convertValue(obj, targetClass);

                return CompletableFuture.runAsync(() -> {
                    strategy.handle(typedPayload, methodsKafka);
                    log.info("{}: handle реализует для [{}], метод [{}]",
                            Thread.currentThread().getName(),
                            strategy.getClass().getSimpleName(),
                            methodsKafka);
                });
            }
        }
       log.error("consume не нашёл стратегии");
        return CompletableFuture.completedFuture(null);
    }

    private Class<?> getClassByName(String className) {
        return switch (className){
            case "EmployeeDTO" -> EmployeeDTO.class;
            case "PhoneNumberDTO" -> PhoneNumberDTO.class;
            case "EmployePhoneDTO" -> EmployePhoneDTO.class;
            case "EmployePhoneFullDTO" -> EmployePhoneFullDTO.class;
            default -> throw new IllegalArgumentException("Unknown class: " + className);
        };
    }


}
