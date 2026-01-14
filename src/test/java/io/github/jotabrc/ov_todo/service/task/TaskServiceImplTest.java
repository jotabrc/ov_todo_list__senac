package io.github.jotabrc.ov_todo.service.task;

import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.domain.task.dto.CategoryDto;
import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import io.github.jotabrc.ov_todo.handler.OvException;
import io.github.jotabrc.ov_todo.service.TaskServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest extends TaskServer {

    @Autowired
    private TaskService taskService;

    @Nested
    class Given_an_task extends TaskServer {

        private TaskDto taskDto;

        @BeforeEach
        void setup() {
            taskDto = TaskDto.builder()
                    .name("Task test")
                    .status(Status.PENDING)
                    .categories(List.of(CategoryDto.builder()
                                    .name("Category test")
                            .build()))
                    .build();
        }

        @Nested
        class When_saving_the_task extends TaskServer {

            private TaskDto savedTask;

            @BeforeEach
            void setup() {
                savedTask = taskService.save(taskDto);
            }

            @Test
            void Then_it_should_be_saved() {
                assertNotNull(savedTask);
                assertEquals(taskDto.getName(), savedTask.getName());
                assertEquals(taskDto.getStatus(), savedTask.getStatus());
                assertEquals(taskDto.getCategories().get(0).getName(), savedTask.getCategories().get(0).getName());
            }
        }

        @Nested
        class When_updating_the_task extends TaskServer {

            private TaskDto savedTask;

            @BeforeEach
            void setup() {
                savedTask = taskService.save(taskDto);
                taskDto.setId(savedTask.getId());
                taskDto.setName("New name");
                taskDto.setStatus(Status.DONE);
                taskDto.setCategories(List.of(CategoryDto.builder()
                        .name("new Category test")
                        .build()));
                savedTask = taskService.update(taskDto);
            }

            @Test
            void Then_it_should_be_updated() {
                assertNotNull(savedTask);
                assertEquals(taskDto.getName(), savedTask.getName());
                assertEquals(taskDto.getStatus(), savedTask.getStatus());
                assertEquals(taskDto.getCategories().get(0).getName(), savedTask.getCategories().get(0).getName());
            }
        }

        @Nested
        class When_deleting_the_task extends TaskServer {

            private TaskDto savedTask;
            private Boolean isDeleted;

            @BeforeEach
            void setup() {
                savedTask = taskService.save(taskDto);
                isDeleted = taskService.delete(savedTask.getId());
            }

            @Test
            void Then_it_should_be_deleted() {
                assertTrue(isDeleted);
                assertThrows(OvException.EntityNotFound.class, () -> taskService.findById(savedTask.getId()));
            }
        }

        @Nested
        class When_finding_the_task_by_id extends TaskServer {

            private TaskDto savedTask;
            private TaskDto foundTask;

            @BeforeEach
            void setup() {
                savedTask = taskService.save(taskDto);
                foundTask = taskService.findById(savedTask.getId());
            }

            @Test
            void Then_it_should_find_by_id() {
                assertNotNull(foundTask);
                assertDoesNotThrow(() -> taskService.findById(savedTask.getId()));
            }
        }

        @Nested
        class When_updating_the_task_status extends TaskServer {

            private TaskDto savedTask;

            @BeforeEach
            void setup() {
                savedTask = taskService.save(taskDto);
                savedTask = taskService.updateStatus(savedTask.getId(), Status.DONE);
                savedTask = taskService.findById(savedTask.getId());
            }

            @Test
            void Then_it_should_be_updated() {
                assertEquals(Status.DONE, savedTask.getStatus());
            }
        }

        @Nested
        class When_finding_tasks_by_status extends TaskServer {

            private Page<TaskDto> savedTask;

            @BeforeEach
            void setup() {
                taskService.save(taskDto);
                savedTask = taskService.findByStatus(Status.PENDING, PageRequest.of(0, 1));
            }

            @Test
            void Then_it_should_find_by_status() {
                assertEquals(1, savedTask.getTotalElements());
                assertEquals(Status.PENDING, savedTask.getContent().get(0).getStatus());
            }
        }

        @Nested
        class When_finding_all_tasks extends TaskServer {

            private Page<TaskDto> savedTask;

            @BeforeEach
            void setup() {
                taskDto.setName("one");
                taskService.save(taskDto);
                taskDto.setName("two");
                taskService.save(taskDto);
                savedTask = taskService.findAll(PageRequest.of(0, 10));
            }

            @Test
            void Then_it_should_find_all() {
                assertEquals(2, savedTask.getTotalElements());
                assertEquals("one", savedTask.getContent().get(0).getName());
                assertEquals("two", savedTask.getContent().get(1).getName());
            }
        }
    }
}