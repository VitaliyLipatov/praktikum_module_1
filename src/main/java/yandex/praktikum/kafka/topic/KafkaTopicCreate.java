package yandex.praktikum.kafka.topic;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class KafkaTopicCreate {

    //Пример создания топика через java-код
    public void create() {
        try (Admin admin = Admin.create(Collections.singletonMap("bootstrap.servers", "localhost:9092"))) {
            NewTopic newTopic = new NewTopic("my-topic", 1, (short) 1);
            admin.createTopics(Collections.singleton(newTopic)).all().get();
            System.out.println("Topic created successfully!");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
