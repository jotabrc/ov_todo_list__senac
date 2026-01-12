package io.github.jotabrc.ov_todo.repository;

import io.github.jotabrc.ov_todo.domain.task.entity.Task;

public interface TaskRepositoryInterface {

    Task findByIdOrElseThrow(Long id);
    Task save(Task task);
    Boolean delete(Long id);
}
