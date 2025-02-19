# praktikum_module_1
Итоговый проект первого модуля Apache Kafka (Яндекс Практикум)

Это стандартное java-приложение, для работы необходимо запустить метод main класса KafkaApplication.
Для запуска предпочтительней использовать IDE (например, IntelliJ IDEA).

До запуска приложения необходимо:
1. Запустить Docker.
2. Скопировать из папки resources файл docker-compose.yml на локальную машину 
и запустить его командой 'docker-compose up -d' через консоль.
3. Создать топик через консоль - команду можно скопировать из файла topic.txt, который лежит в resources.

После запуска приложения класс Producer начнёт отправлять сообщения в first-module и писать их содержимое в консоль.
Консьюмеры PullConsumer и PushConsumer начнут читать данные сообщения 
и также писать информацию о прочитанных записях в консоль
Внутри самих классов по коду есть комментарии какая настройка за что отвечает.

Пример сообщений в консоле:

Message message-4 was successfully sent in topic first-module
PushConsumer got message from topic first-module: key = key-4, value = message-4, partition = 0 offset = 1
PullConsumer got message from topic first-module: key = key-4, value = message-4, partition = 0 offset = 1
