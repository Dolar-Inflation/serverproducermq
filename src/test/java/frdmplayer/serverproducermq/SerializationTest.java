package frdmplayer.serverproducermq;

import frdmplayer.DTO.EmployeeDTO;
import frdmplayer.KafkaMethods.MethodsKafka;
import frdmplayer.services.Consumer;
import frdmplayer.services.Producer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"test-topic"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SerializationTest {
    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Test
    public void testSerialization() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFio("zzz");
        dto.setAddress("svosvosvo");
        dto.setPosition("vvvvvv");
        producer.send(dto, MethodsKafka.CREATE);

        Map<String, Object> consumerProps =
                KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafka);
        ConsumerFactory<String, String> cf =
                new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new StringDeserializer());
        org.apache.kafka.clients.consumer.Consumer<String, String> consumer = cf.createConsumer();
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, "test-topic");

        ConsumerRecord<String, String> record =
                KafkaTestUtils.getSingleRecord(consumer, "test-topic");

        String value = record.value();
        assertTrue(value.contains("\"fio\":\"zzz\""));
        assertTrue(value.contains("\"address\":\"svosvosvo\""));
        assertTrue(value.contains("\"position\":\"vvvvvv\""));
    }
}
