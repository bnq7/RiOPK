
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

        Optional<Resource> deletedResource = resourceRepository.findById(testResource.getId());
        // Проверяем, что ресурс был удален
        assert deletedResource.isEmpty();
    }

    @Test
    void testResourceNotFound() throws Exception {
        mockMvc.perform(get("/api/resources/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}

