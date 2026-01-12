package io.github.jotabrc.ov_todo.service.task;

import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import io.github.jotabrc.ov_todo.service.StrategyCommand;
import io.github.jotabrc.ov_todo.service.task.executor.TaskExecutor;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskExecutor taskExecutor;

    @Override
    public TaskDto save(@Validated TaskDto taskDto) {
        return taskExecutor.execute(taskDto, taskDto.getClass(), StrategyCommand.INSERT);
    }

    @Override
    public TaskDto findById(Long id) {
        Objects.requireNonNull(id, "Finding an entity requires it's id");
        return taskExecutor.execute(id, TaskDto.class, StrategyCommand.SELECT);
    }

    @Override
    public TaskDto update(@Validated TaskDto taskDto) {
        Objects.requireNonNull(taskDto.getId(), "Updating an entity requires it's id");
        return taskExecutor.execute(taskDto, TaskDto.class, StrategyCommand.UPDATE);
    }

    @Override
    public Boolean delete(@NotNull Long id) {
        Objects.requireNonNull(id, "Deleting an entity requires it's id");
        return taskExecutor.execute(id, Boolean.class, StrategyCommand.DELETE);
    }
}
