package frdmplayer.serverproducermq;

import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.services.Consume;
import frdmplayer.services.Consumer;
import frdmplayer.services.KafkaObertka;
import frdmplayer.services.Producer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.yetus.audience.InterfaceAudience;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SerializationDeserializationTest {
    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

//    public SerializationDeserializationTest(Producer producer, Consume consume) {
//        this.producer = producer;
//        this.consume = consume;
//    }

    @Test
    public void testDeserialization() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFio("zzz");
        dto.setAddress("svosvosvo");
        dto.setPosition("vvvvvv");
        producer.send(dto,MethodsKafka.CREATE);
        String className = dto.getClass().getSimpleName();
        KafkaObertka obertka = new KafkaObertka(dto,MethodsKafka.CREATE, className);
//        CompletableFuture<Void> future = consumer.consumer(obertka);
//        future.get();
        Object payload = obertka.getPayload();

        assertEquals(EmployeeDTO.class,payload.getClass());



    }
//    @Test
//    public void testSerialization() throws Exception {
//        EmployeeDTO dto = new EmployeeDTO();
//        dto.setFio("zzz");
//        dto.setAddress("svosvosvo");
//        dto.setPosition("vvvvvv");
//        producer.send(dto,MethodsKafka.CREATE);
//        KafkaObertka obertka = new KafkaObertka(dto,MethodsKafka.CREATE, dto.getClass().getSimpleName());
//
//    }

}
