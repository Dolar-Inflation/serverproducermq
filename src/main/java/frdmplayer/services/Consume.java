package frdmplayer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import frdmplayer.DTO.EmployePhoneDTO;
import frdmplayer.DTO.EmployePhoneFullDTO;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.DTO.PhoneNumberDTO;
import frdmplayer.Interfaces.KafkaConsumerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class

Consume {
    private final List<KafkaConsumerStrategy> strategies;
    private final ObjectMapper mapper;

    @Autowired
    private ExecutorService executorService;

//    @Async
    public CompletableFuture<Void> consume(Object obj,String objClassName, MethodsKafka methodsKafka) {
//        String payloadClassName = obj.getClass().getName();
        for (KafkaConsumerStrategy strategy : strategies) {
            if(strategy.getClassName().equals(objClassName)) {
                Class<?> targetClass = getClassByName(objClassName);
                Object typedPayload = mapper.convertValue(obj, targetClass);
//                strategy.handle(typedPayload, methodsKafka);
                //System.out.println(Thread.currentThread().getName() + ": Consumed: " + typedPayload);
                return CompletableFuture.runAsync(() -> {
                    strategy.handle(typedPayload, methodsKafka);
                    System.out.println(Thread.currentThread().getName() + ": Принял : " + typedPayload);

                },executorService);
            }
        }
        System.out.println("нихуя не сработало consume");
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
