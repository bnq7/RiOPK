
С4 модель первого уровня

 ![image](https://github.com/user-attachments/assets/9379ece2-8cb0-49cb-8f2f-6528618ccb3b)
 
Второй уровень С4- модели

![image](https://github.com/user-attachments/assets/f88e510f-dc75-455f-a087-931179295a87)

С4- модель третьего уровня

![image](https://github.com/user-attachments/assets/0b5ac44c-8836-4fcb-91dc-41a4314b8e5b)

Диаграмма классов

![image](https://github.com/user-attachments/assets/f60211e6-3443-4343-a109-d156573bbbd5)

<h1>Архитектура</h1>

Реляционная схема базы данных

![image](https://github.com/user-attachments/assets/7a74fb6b-a68e-4237-a37e-dce93a010db7)

![image](https://github.com/user-attachments/assets/d112f400-d6ab-4cf0-878d-2022de491ed7)

Диаграмма последовательности

![image](https://github.com/user-attachments/assets/433cc0af-e196-4e3d-901d-9f3ac1e38d83)

Диаграмма вариантов использования

Серверная часть с помощью Swagger

![image](https://github.com/user-attachments/assets/4b9f3132-1319-4cb2-a688-337e67c3faf8)

Результаты расчетов в SonarCube

![image](https://github.com/user-attachments/assets/a5882055-b266-460e-9127-a8ac80ba5f7f)

Результат Junit-тестов

![image](https://github.com/user-attachments/assets/89fb4f32-21a9-411a-a525-785989b44328)

В этом тесте проверяется функциональность сервисного слоя для работы с сущностью Content. Он включает несколько тестов для различных операций с контентом: findAllSuccess: проверяет, что метод получения всех контентов возвращает корректный список записей. Репозиторий имитирует возврат заранее подготовленных данных, а тест удостоверяется, что их количество совпадает с ожидаемым.   findByIdSuccess: проверяет, что метод получения контента по ID возвращает корректный объект с правильными значениями всех его полей.findByIdNotFound: имитирует ситуацию, когда запрашиваемый контент не найден. Ожидается выброс исключения ObjectNotFoundException, а также подтверждается, что репозиторий был вызван один раз. saveSuccess: тестирует успешное сохранение объекта контента. Репозиторий имитирует возврат сохраненного объекта, а тест проверяет, что все его поля соответствуют ожидаемым значениям.
updateSuccess: проверяет успешное обновление существующего контента. Репозиторий находит объект по ID, обновляет его данными, переданными в метод, и возвращает обновленный объект. Тест подтверждает, что обновленные поля совпадают с ожидаемыми.

Интеграционные тесты – это тип тестирования программного обеспечения, который фокусируется на проверке взаимодействий между различными компонентами или модулями приложения. В отличие от юнит-тестов, которые тестируют отдельные единицы функциональности в изоляции, интеграционные тесты проверяют, как различные части системы работают друг с другом в совокупности. Это помогает гарантировать, что интеграция модулей системы была успешной и что они совместно функционируют согласно спецификации.
Основные характеристики интеграционных тестов:
Тестируется взаимодействие компонентов вместо того чтобы тестировать код в изоляции. Интеграционные тесты проверяют зависимость между модулями и их взаимодействие в более сложных сценариях.
Часто включают тестирование взаимодействий с внешними системами, такими как базы данных, веб-сервисы, файловые системы или сетевые соединения.
Находятся между юнит-тестами и сквозным тестированием по уровню охвата (иногда называются компонентным тестированием в более широком смысле).
Поскольку включает работу с несколькими компонентами системы, они могут быть сложнее в написании и поддержании по сравнению с юнит-тестами.
Интеграционные тесты часто запускаются в среде, которая closely моделирует production окружение.
Преимущества интеграционных тестов:
Обнаружение проблем на уровне интеграции
Уверенность в совместной работоспособности
Поддержка разработчиков и изменений функциональности
Пример интеграционного теста с использованием Spring Boot и Junit:

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.model.Resource;
import com.example.demo.service.ResourceService;
import com.example.demo.repository.ResourceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceService resourceService;

    private Resource testResource;

    @BeforeEach
    public void setup() {
        // Создаем тестовый ресурс
        testResource = new Resource(1L, "Test Resource", "https://test.com", 85.0f, "Test description");
        resourceRepository.save(testResource); // Сохраняем в базу данных для тестов
    }

    @Test
    void testCreateResource() throws Exception {
        Resource newResource = new Resource(null, "New Resource", "https://new.com", 90.0f, "New description");

        mockMvc.perform(post("/api/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newResource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("New Resource")))
                .andExpect(jsonPath("$.url", is("https://new.com")))
                .andExpect(jsonPath("$.rating", is(90.0)))
                .andExpect(jsonPath("$.description", is("New description")));
    }

    @Test
    void testGetResourceById() throws Exception {
        mockMvc.perform(get("/api/resources/{id}", testResource.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(testResource.getName())))
                .andExpect(jsonPath("$.url", is(testResource.getUrl())))
                .andExpect(jsonPath("$.rating", is((double) testResource.getRating())))
                .andExpect(jsonPath("$.description", is(testResource.getDescription())));
    }

    @Test
    void testUpdateResource() throws Exception {
        Resource updatedResource = new Resource(testResource.getId(), "Updated Resource", "https://updated.com", 95.0f, "Updated description");

        mockMvc.perform(put("/api/resources/{id}", testResource.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Resource")))
                .andExpect(jsonPath("$.url", is("https://updated.com")))
                .andExpect(jsonPath("$.rating", is(95.0)))
                .andExpect(jsonPath("$.description", is("Updated description")));
    }

    @Test
    void testDeleteResource() throws Exception {
        mockMvc.perform(delete("/api/resources/{id}", testResource.getId()))
                .andExpect(status().isNoContent());

        Optional  deletedResource = resourceRepository.findById(testResource.getId());
        // Проверяем, что ресурс был удален
        assert deletedResource.isEmpty();
    }

    @Test
    void testResourceNotFound() throws Exception {
        mockMvc.perform(get("/api/resources/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}

<h1>Пользовательский интерфейс</h1>

<h2>User-flow диаграммы</h2>

![image](https://github.com/user-attachments/assets/992bf75f-14ed-4bfb-8019-09b730500025)

User Flow для пользователя

![image](https://github.com/user-attachments/assets/957e76a8-a716-4e11-8013-8e6e02ee2022)

User Flow для администратора

<h2>Примеры экранов UI</h2>

![image](https://github.com/user-attachments/assets/e034208b-492e-4cbb-bcd7-e2c1ebf16f32)

Главная страница

![image](https://github.com/user-attachments/assets/498b34ed-9afc-48fe-bd52-7d967a12b952)

Профиль пользователя

![image](https://github.com/user-attachments/assets/b9f0573c-22a0-4b63-8769-7904cb62fad7)

Ввод сайта

![image](https://github.com/user-attachments/assets/5dc4820f-c3e2-483a-b7c7-6108c3be2fb9)

Выбор оценки

системы управления зависимостями Maven.

https://github.com/bnq7/RiOPK/blob/main/webresources/123/pom.xml

![image](https://github.com/user-attachments/assets/58b9857b-4c1e-4f1b-a8f0-3780da7841de)

Пример JWT

https://github.com/bnq7/RiOPK/blob/main/webresources/123/src/main/java/com/webresources/config/JwtUtil.java

![image](https://github.com/user-attachments/assets/384c09a6-1c61-4ff8-b7d8-075bf1cf71ec)

наличие токена в каждом запросе(JWT)

https://github.com/bnq7/RiOPK/blob/main/webresources/123/src/main/java/com/webresources/config/JwtAuthenticationFilter.java

![image](https://github.com/user-attachments/assets/83dc3858-e41f-4a77-8956-4521a3233ecc)

Docker

https://github.com/bnq7/RiOPK/blob/main/webresources/123/src/Dockerfile

https://github.com/bnq7/RiOPK/blob/main/webresources/123/src/docker-compose.yml

1 Docker Engine: основной движок, который управляет контейнерами и обеспечивает их выполнение. Он состоит из демона Docker, REST API и командной строки Docker CLI.
2 Docker Images: образы, которые содержат все необходимое для работы приложения, включая операционную систему, исполняемые файлы, зависимости и настройки. Образы являются основой для создания контейнеров.
3 Docker Containers: изолированные среды, которые запускаются на основе образов. Они обеспечивают безопасность и консистентность работы приложений независимо от окружения, в котором они развернуты.
4 Docker Compose: инструмент, который позволяет определять и запускать многоконтейнерные приложения с помощью конфигурационного файла docker-compose.yml. С его помощью можно описывать взаимосвязи между сервисами, порты, сетевые настройки и переменные окружения.
Использование Docker упрощает управление зависимостями, так как весь необходимый код и библиотеки поставляются вместе с приложением в контейнере. Это делает разработку более надежной и гарантирует, что приложение будет работать одинаково на любой машине, будь то сервер разработчика, тестовая среда или продакшн. Docker также значительно упрощает процесс масштабирования и управления большими распределенными системами.

Скрипт файла docker-compose.yml:

version: '3.8'
services:
  db:
    image: mysql:8.0
    container_name: mysql-db
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: appdb
      MYSQL_USER: root
      MYSQL_PASSWORD: root
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql

  api:
    image: openjdk:17-jdk-slim
    container_name: spring-api
    build:
      context: ./api
      dockerfile: Dockerfile
    environment:
      - SPRING_PROFILES_ACTIVE=dev
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/appdb
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
    ports:
      - "8080:8080"
    depends_on:
      - db

  Client:
    image: node:18-alpine
    container_name: react-client
    build:
      context: ./client
      dockerfile: Dockerfile
    ports:
      - "3000:3000"
    volumes:
      - ./client:/app
      - /app/node_modules
    depends_on:
      - api

volumes:
  db_data:

Dockerfile для Spring Boot (Java):

# Этап сборки
FROM openjdk:17-jdk-slim AS build
WORKDIR /src
Copy . .
RUN ./mvnw clean package -DskipTests

# Этап выполнения
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /src/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

Docker file для React-приложения:

FROM node:18-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install
Copy . .
EXPOSE 3000
CMD ["npm", "start"]











