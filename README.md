# Custom ThreadPool Executor

Учебный проект — реализация собственного пула потоков на Java без использования `java.util.concurrent`.

## Технологии

- Java 17
- Maven

## Структура

- `BlockingQueue` — блокирующая очередь на базе `ArrayList` с `wait()/notifyAll()`
- `CustomThreadPool` — пул потоков с фиксированным количеством воркеров
- `Application` — тестовый запуск

## Сборка

```bash
mvn compile
```


