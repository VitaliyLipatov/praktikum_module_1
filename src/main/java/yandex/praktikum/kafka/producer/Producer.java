package yandex.praktikum.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Producer {

    @Scheduled(fixedDelay = 1000L)
    public void send() {
        // Конфигурация продюсера – адрес сервера, сериализаторы для ключа и значения.
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9095, localhost:9096");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Создание продюсера
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Отправка сообщения
        ProducerRecord<String, String> record = new ProducerRecord<>("first-module", "key-1", "message-1");
        producer.send(record);
        System.out.printf("Message %s was successfully sent in topic first-module", record.value());

        // Закрытие продюсера
        producer.close();
    }
}
