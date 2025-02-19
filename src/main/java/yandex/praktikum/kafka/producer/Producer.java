package yandex.praktikum.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public void send() {
        // Конфигурация продюсера – адрес сервера, сериализаторы для ключа и значения.
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9095, localhost:9096");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Синхронная репликация - требует, чтобы все реплики синхронно подтвердили получение сообщения,
        // только после этого оно считается успешно отправленным
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        // Количество повторных попыток при отправке сообщений, если возникает ошибка.
        // Если три раза произошли ошибки, то сообщение считается неотправленным и ошибка будет возвращена продюсеру.
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        // Минимум 2 реплики должны подтвердить запись
        properties.put(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2");

        // Создание продюсера
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Отправка 20 сообщений в топик с паузой в 1 секунду после каждой отправки
        for (int i = 0; i < 20; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("first-module", "key-" + i, "message-" + i);
            producer.send(record);
            System.out.printf("Message %s was successfully sent in topic first-module", record.value());
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            }
        }

        // Закрытие продюсера
        producer.close();
    }
}
