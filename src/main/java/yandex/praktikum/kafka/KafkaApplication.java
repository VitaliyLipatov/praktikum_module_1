package yandex.praktikum.kafka;

import yandex.praktikum.kafka.consumer.PullConsumer;
import yandex.praktikum.kafka.consumer.PushConsumer;
import yandex.praktikum.kafka.producer.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaApplication {

	public static void main(String[] args) {
		// Запуск чтения консьюмера с использованием модели pull
		ExecutorService pullConsumerExecutorService = Executors.newSingleThreadExecutor();
		pullConsumerExecutorService.submit(() -> new PullConsumer().read());
		// Запуск чтения консьюмера с использованием модели push
		ExecutorService pushConsumerExecutorService = Executors.newSingleThreadExecutor();
		pushConsumerExecutorService.submit(() -> new PushConsumer().read());
		// Запуск продьюсера
		ExecutorService producerExecutorService = Executors.newSingleThreadExecutor();
		producerExecutorService.submit(() -> new Producer().send());
	}
}
