package io.github.jotabrc.ov_todo.repository;

import io.github.jotabrc.ov_todo.domain.task.entity.Task;
import io.github.jotabrc.ov_todo.handler.OvException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskRepositoryImpl implements TaskRepositoryInterface {

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
}
