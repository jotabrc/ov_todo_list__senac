package io.github.jotabrc.ov_todo.repository;

import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.domain.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepositoryInterface {

    Task findByIdOrElseThrow(Long id);
    Task save(Task task);
    Boolean delete(Long id);
    Page<Task> findByStatus(Status status, Pageable pageable);
    Page<Task> findAll(Pageable pageable);
}
