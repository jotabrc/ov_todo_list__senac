package io.github.jotabrc.ov_todo.repository;

import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.domain.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepository extends BaseRepository<Task, Long> {

    Page<Task> findByStatus(Status status, Pageable pageable);
}
