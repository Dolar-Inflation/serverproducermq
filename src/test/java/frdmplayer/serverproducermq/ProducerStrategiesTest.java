package frdmplayer.serverproducermq;


import com.fasterxml.jackson.core.JsonProcessingException;
import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.Entity.Employee;
import frdmplayer.Interfaces.KafkaProducerStrategy;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.Strategies.Producer.EmployeeDTOProducerStrategy;
import frdmplayer.services.Producer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

@ExtendWith({MockitoExtension.class})
public class ProducerStrategiesTest {

    @InjectMocks
    Producer producer;
    @Mock
    KafkaProducerStrategy strategies;
    @Mock
    ExecutorService executorService;
    @Mock
    EmployeeDTOProducerStrategy employeeDTOProducerStrategy;

    @Mock
    Semaphore semaphore;
    @Mock
    Logger log;




@Transactional
    @Test
    public void testProducerStrategies() throws JsonProcessingException {

    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setFio("sdfsd");
    employeeDTO.setPosition("sdadadasd");
    employeeDTO.setPosition("sdkisddiodxsi");
    employeeDTO.setAddress("skdomiokimwomikkmi");
    MethodsKafka kafka = MethodsKafka.CREATE;

    ReflectionTestUtils.setField(producer, "strategies", List.of(employeeDTOProducerStrategy));

    Mockito.when(employeeDTOProducerStrategy.supports(employeeDTO,kafka)).thenReturn(Boolean.TRUE);
    Mockito.doNothing().when(employeeDTOProducerStrategy).send(Mockito.any());

//    Mockito.when(executorService.submit(Mockito.any(Runnable.class))).thenReturn(null);
    Mockito.when(executorService.submit(Mockito.any(Runnable.class)))
            .thenAnswer(inv -> {
                Runnable r = inv.getArgument(0);
                r.run();
                return null;
            });

    Mockito.when(semaphore.tryAcquire()).thenReturn(true);
    Mockito.when(semaphore.availablePermits()).thenReturn(1);

    producer.send(employeeDTO,  kafka);

    Assertions.assertTrue(semaphore.tryAcquire());
    Mockito.verify(employeeDTOProducerStrategy, Mockito.times(1)).send(Mockito.any());




    }


}
