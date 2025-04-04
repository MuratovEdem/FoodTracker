Задача: Необходимо создать REST API сервис для отслеживания дневной нормы калорий пользователя и учета съеденных блюд.

Стек технологий: Java 17, Spring Boot, Spring Data Jpa, PostgreSQL, Docker, Mockito, Hibernate

Требования:
1. Пользователи. Добавление пользователей с параметрами:
- ID
- Имя
- Email
- Возраст
- Вес
- Рост
- Цель (Похудение, Поддержание, Набор массы)
 
На основе данных автоматически рассчитать дневную норму калорий (можно использовать формулу Харриса-Бенедикта).
2. Блюда. Добавление блюд с параметрами:
- ID
- Название
- Количество калорий на порцию
- Белки/Жиры/Углеводы
3. Прием пищи. Пользователь может добавлять прием пищи со списком блюд 
4. Отчеты (эндпоинты, без формирования документа):
- отчет за день с суммой всех калорий и приемов пищи;
- проверка, уложился ли пользователь в свою дневную норму калорий;
- история питания по дням.
 
Нефункциональные требования:
- использовать Spring Boot + Spring Data JPA;
- база данных PostgreSQL;
- валидация входных данных (например, проверка веса и роста на адекватность);
- написать юнит-тесты для основной логики;
- реализовать обработку ошибок (например, если пользователь не найден).

Инструкция запуска: необходимо запустить Docker-контейнеры из файла docker-compose.yml

Postman-коллекция: 
- Создать пользователя. Ручка - (http://localhost:8080/clients)
  Json объект -
      {
        "name": "testname2",
        "email": "testemail",
        "age": 25,
        "weight": 67,
        "height": 174
      }
- Создать блюдо. Ручка - (http://localhost:8080/dishes)
  Json объект -
      {
        "name": "testDish1",
        "caloriesPerServing": 19,
        "proteins": 15.5,
        "fats": 7.7,
        "carbohydrates": 5.5
      }
- Добавить прием пищи. Ручка - (http://localhost:8080/foodintake)
  Json объект -
  {
    "date": "2025-03-25",
    "client": {
        "id": 1
    },
    "dishes": [
    {
            "id": 1
        },
        {
            "id": 2
        }
    ]
}
- Получить список блюд в определенный день. Ручка - (http://localhost:8080/foodintake/{clientId}?date=2025-03-25)
- Получить отчет по калориям за определенный день. Ручка - (http://localhost:8080/foodintake/{clientId}/report?date=2025-03-25)







  
