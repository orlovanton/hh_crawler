# HHCrawler
Приложение для отображения вакансий, полученных из API Head Hunter по поисковому запросу "java".
Данное приложение может выводить вакансии на веб-страницу, записывать в БД, а так же сохранять их в .txt файл.

#### Требования:
- JDK 1.8
- Apache Maven 3.x
- БД MySQL

#### Подготовка к запуску приложения:
-   Создать БД vacancy
-   Исполнить sql запрос, из файла src\main\resources\createtabledql.sql
-   Прописать корректный путь txt файла в src\main\resources\application.properties в пременной failpath
-   В случае работы приложения с БД неоходимо указать в src\main\resources\application.properties  hhmode=db
-   При работе с txt файлом неоходимо указать в src\main\resources\application.properties  hhmode=file

#### Запуск приложения из Intellij Idea:
- Запустить класс src\main\java\ru\af\web\Application.java
- Зайти на localhost:8080

#### Запуск приложения из консоли:
- mvn clean package
- java -jar /target/hh_crawler-1.0-SNAPSHOT.jar
- Зайти на localhost:8080