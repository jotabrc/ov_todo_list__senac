package io.github.jotabrc.ov_todo.repository;

import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.domain.task.entity.Task;
import io.github.jotabrc.ov_todo.handler.OvException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskDefaultDefaultRepositoryImpl implements TaskDefaultRepository {

    private final TaskRepository taskRepository;

    @Override
    public Task findByIdOrElseThrow(@NotNull Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new OvException.EntityNotFound("Entity not found with id %d".formatted(id)));
    }

    @Override
    public Task save(@NotNull Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Boolean delete(Long id) {
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Task> findByStatus(Status status, Pageable pageable) {
        return taskRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
