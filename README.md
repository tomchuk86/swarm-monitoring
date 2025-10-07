## Часть 1 Получение метрик и логов

### Написание сборщиков метрик

Добавление micrometer в pom.xml

![](images/1.png)

Добавление настроей в aplication.config

![](images/2.png)

Пример добавления счётчика в код 

![](images/3.png)

Вывод полученных метрик в prometheus

![](images/4.png)

### Добавление стека мониторинга в docker-compose

![](images/12.png)
![](images/13.png)
![](images/14.png)

Просмотр сборщиков метрик

![](images/5.png)
![](images/6.png)

## Часть 2 Визуализация

Добавление Grafana

![](images/15.png)

Подключенные data sourses

![](images/11.png)

Dashbord с метриками

![](images/7.png)
![](images/8.png)
![](images/9.png)
![](images/10.png)

## Часть 3 Отслеживание критических событий

Добавление Alert Manager

![](images/16.png)

Настройки отслеживания критических событий

![](images/17.png)

Отображение alert manager в prometheus

![](images/18.png)

Имитация нагрузки

![](images/22.png)

Срабатывание оповещения

![](images/19.png)
![](images/20.png)

Сообщение о стабилизации
![](images/21.png)

Сообщение о ram > 1gb

![](images/23.png)
