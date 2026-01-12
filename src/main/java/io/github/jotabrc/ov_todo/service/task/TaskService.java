package io.github.jotabrc.ov_todo.service.task;

import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    TaskDto save(TaskDto taskDto);
    TaskDto findById(Long id);
    TaskDto update(TaskDto taskDto);
    Boolean delete(Long id);
    TaskDto updateStatus(Long id, Status newStatus);
    Page<TaskDto> findByStatus(Status status, Pageable pageable);
    Page<TaskDto> findAll(Pageable pageable);
}
