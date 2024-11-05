# Лабораторная работа №3. Работа с базой данных.
Код приложения написан на языке Java и использует Android SDK.

## Инструкция по использованию приложения
Данное приложение создано на основе того, что представлено в Task3.1.
Переопределена функция `onUpgrade`. При изменении версии БД таблица удаляется и создается новая таблица
«Одногруппники» содержащая следующие поля:
- ID;
- Фамилия;
- Имя;
- Отчество;
- Время добавления записи.
```java
    public static final String TABLE_NAME = "groupmates";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PATRONYMIC = "patronymic";
    public static final String COLUMN_TIMESTAMP = "timestamp";
```
Изменяется версия базы данных для обновления внесенных изменений в таблицу.
```java
 private static final int DATABASE_VERSION = 3;
```
