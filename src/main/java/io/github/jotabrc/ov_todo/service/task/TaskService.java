package io.github.jotabrc.ov_todo.service.task;

import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;

public interface TaskService {

    TaskDto save(TaskDto taskDto);
    TaskDto findById(Long id);
    TaskDto update(TaskDto taskDto);
    Boolean delete(Long id);
}
