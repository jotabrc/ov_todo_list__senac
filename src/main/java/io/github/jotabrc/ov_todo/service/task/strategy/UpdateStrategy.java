package io.github.jotabrc.ov_todo.service.task.strategy;

import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import io.github.jotabrc.ov_todo.domain.task.entity.Category;
import io.github.jotabrc.ov_todo.domain.task.entity.Task;
import io.github.jotabrc.ov_todo.mapper.TaskMapper;
import io.github.jotabrc.ov_todo.repository.TaskDefaultRepository;
import io.github.jotabrc.ov_todo.service.BaseStrategy;
import io.github.jotabrc.ov_todo.service.StrategyCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateStrategy implements BaseStrategy<TaskDto, TaskDto> {

    private final TaskMapper taskMapper;
    private final TaskDefaultRepository taskRepository;
    private final MapperBuilder mapperBuilder;

    @Override
    public TaskDto execute(TaskDto taskDto) {
        return taskRepository.findById(taskDto.getId())
                .map(task -> {
                    updateTask(taskDto, task);
                    return taskMapper.toTaskDto(taskRepository.save(task));
                }).orElse(null);
    }

    private void updateTask(TaskDto taskDto, Task task) {
        task.setName(taskDto.getName());
        task.setStatus(taskDto.getStatus());

        Set<Category> newCategories = Optional.ofNullable(taskDto.getCategories())
                .orElse(Collections.emptyList())
                .stream()
                .filter(category -> Objects.nonNull(category.getName()) && !category.getName().isBlank())
                .map(taskMapper::toCategory)
                .collect(Collectors.toSet());

        task.updateCategories(newCategories);
    }

    @Override
    public StrategyCommand supports() {
        return StrategyCommand.UPDATE;
    }
}
