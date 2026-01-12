package io.github.jotabrc.ov_todo.service.task.strategy;

import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import io.github.jotabrc.ov_todo.domain.task.entity.Task;
import io.github.jotabrc.ov_todo.mapper.TaskMapper;
import io.github.jotabrc.ov_todo.repository.TaskRepositoryInterface;
import io.github.jotabrc.ov_todo.service.BaseStrategy;
import io.github.jotabrc.ov_todo.service.StrategyCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateStrategy implements BaseStrategy<TaskDto, TaskDto> {

    private final TaskMapper taskMapper;
    private final TaskRepositoryInterface taskRepository;

    @Override
    public TaskDto execute(TaskDto taskDto) {
        Task task = taskRepository.findByIdOrElseThrow(taskDto.getId());
        updateTask(taskDto, task);
        return taskMapper.toTaskDto(taskRepository.save(task));
    }

    private void updateTask(TaskDto taskDto, Task task) {
        task.setName(taskDto.getName());
        task.setStatus(taskDto.getStatus());
        task.getCategories()
                .addAll(taskDto.getCategories()
                        .stream()
                        .map(taskMapper::toCategory)
                        .toList());
    }

    @Override
    public StrategyCommand supports() {
        return StrategyCommand.UPDATE;
    }
}
