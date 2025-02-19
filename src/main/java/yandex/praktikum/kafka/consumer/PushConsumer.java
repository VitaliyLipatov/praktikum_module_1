package yandex.praktikum.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import yandex.praktikum.kafka.producer.Producer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class PushConsumer {

    public void read() {
        // Настройка консьюмера – адрес сервера, сериализаторы для ключа и значения
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9095, localhost:9096");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "first-module-group-2");     // наименование группы консьюмеров
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");        // Начало чтения с самого начала
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");           // Автоматический коммит смещений (для push модели консьюмера включен)
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "6000");           // Время ожидания активности от консьюмера

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Подписка на топик
        consumer.subscribe(Collections.singletonList("first-module"));

        // Чтение сообщений
        while (true) {
            // Устанавливаем таймаут ожидания равный нулю, чтобы сообщения читались сразу после поступления
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(0));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("PushConsumer got message from topic first-module: key = %s, value = %s, "
                                + "partition = %d offset = %s%n",
                        record.key(), record.value(), record.partition(), record.offset());;
            }
        }
    }
}
