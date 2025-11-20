package frdmplayer.serverproducermq;

import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.services.Consumer;
import frdmplayer.services.Producer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"test-topic"})
public class DeserializationTest {
    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @Test
    public void testDeserialization() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFio("zzz");
        dto.setAddress("svosvosvo");
        dto.setPosition("vvvvvv");
        producer.send(dto, MethodsKafka.CREATE);

        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS)
                .until(() -> consumer.getConsumed() != null);

        Object payload = consumer.getConsumed();

        assertEquals(LinkedHashMap.class, payload.getClass());
    }
}
