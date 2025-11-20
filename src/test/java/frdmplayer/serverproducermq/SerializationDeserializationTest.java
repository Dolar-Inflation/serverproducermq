//package frdmplayer.serverproducermq;
//
//import frdmplayer.DTO.EmployeeDTO;
//import frdmplayer.KafkaMethods.MethodsKafka;
//import frdmplayer.services.Consume;
//import frdmplayer.services.Consumer;
//import frdmplayer.services.KafkaObertka;
//import frdmplayer.services.Producer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.yetus.audience.InterfaceAudience;
//import org.awaitility.Awaitility;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.test.EmbeddedKafkaBroker;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//
//import org.springframework.kafka.test.utils.KafkaTestUtils;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//
//import java.time.Duration;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@EmbeddedKafka(partitions = 1, topics = {"test-topic"})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class SerializationDeserializationTest {
//    @Autowired
//    private Producer producer;
//
//    @Autowired
//    private Consumer consumer;
//
//    @Autowired
//    private EmbeddedKafkaBroker embeddedKafka;
//
//
//    @Test
//    public void testDeserialization() throws Exception {
//        EmployeeDTO dto = new EmployeeDTO();
//        dto.setFio("zzz");
//        dto.setAddress("svosvosvo");
//        dto.setPosition("vvvvvv");
//        producer.send(dto,MethodsKafka.CREATE);
//
//        Awaitility.await()
//                .atMost(10, TimeUnit.SECONDS)
//                .until(() -> consumer.getConsumed() != null);
//
//        Object payload = consumer.getConsumed();
//
//        assertEquals(LinkedHashMap.class, payload.getClass());
//    }
//
//
//
//
//    @Test
//    public void testSerialization() throws Exception {
//        EmployeeDTO dto = new EmployeeDTO();
//        dto.setFio("zzz");
//        dto.setAddress("svosvosvo");
//        dto.setPosition("vvvvvv");
//        producer.send(dto,MethodsKafka.CREATE);
//
//        Map<String, Object> consumerProps =
//                KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafka);
//        ConsumerFactory<String, String> cf =
//                new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new StringDeserializer());
//        org.apache.kafka.clients.consumer.Consumer<String, String> consumer = cf.createConsumer();
//        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, "test-topic");
//
//        ConsumerRecord<String, String> record =
//                KafkaTestUtils.getSingleRecord(consumer, "test-topic");
//
//        String value = record.value();
//        assertTrue(value.contains("\"fio\":\"zzz\""));
//        assertTrue(value.contains("\"address\":\"svosvosvo\""));
//        assertTrue(value.contains("\"position\":\"vvvvvv\""));
//    }
//
//    }
//
//
