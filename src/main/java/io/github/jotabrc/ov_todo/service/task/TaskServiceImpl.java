package io.github.jotabrc.ov_todo.service.task;

import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import io.github.jotabrc.ov_todo.mapper.TaskMapper;
import io.github.jotabrc.ov_todo.repository.TaskDefaultRepository;
import io.github.jotabrc.ov_todo.service.StrategyCommand;
import io.github.jotabrc.ov_todo.service.task.executor.TaskExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskExecutor taskExecutor;
    private final TaskDefaultRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDto save(@Validated TaskDto taskDto) {
        return taskExecutor.execute(taskDto, taskDto.getClass(), StrategyCommand.INSERT);
    }

    @Override
    public TaskDto findById(Long id) {
        Objects.requireNonNull(id, "Entity ID");
        return taskExecutor.execute(id, TaskDto.class, StrategyCommand.SELECT);
    }

    @Override
    public TaskDto update(@Validated TaskDto taskDto) {
        Objects.requireNonNull(taskDto.getId(), "Entity ID");
        return taskExecutor.execute(taskDto, TaskDto.class, StrategyCommand.UPDATE);
    }

    @Override
    public Boolean delete(Long id) {
        Objects.requireNonNull(id, "Entity ID");
        return taskExecutor.execute(id, Boolean.class, StrategyCommand.DELETE);
    }

    @Override
    public TaskDto updateStatus(Long id, Status newStatus) {
        Objects.requireNonNull(id, "Entity ID");
        Objects.requireNonNull(newStatus, "Status");
        return taskRepository.findById(id)
                .map(task -> {
                    if (!Objects.equals(task.getStatus(), newStatus))
                        task = taskRepository.save(task);
                    return taskMapper.toTaskDto(task);
                }).orElse(null);
    }

    @Override
    public Page<TaskDto> findByStatus(Status status, Pageable pageable) {
        Objects.requireNonNull(status, "Status");
        return taskRepository.findByStatus(status, pageable).map(taskMapper::toTaskDto);
    }

    @Override
    public Page<TaskDto> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::toTaskDto);
    }

    @Override
    public List<TaskDto> find(Long id, String status, Pageable pageable) {
        if (isNull(id) && nonNull(status) && !status.isBlank())
            return this.findByStatus(Status.valueOf(status), pageable).toList();
        else {
            TaskDto taskDto = this.findById(id);
            return nonNull(taskDto)
                    ? List.of(taskDto)
                    : Collections.emptyList();
        }
    }
}
