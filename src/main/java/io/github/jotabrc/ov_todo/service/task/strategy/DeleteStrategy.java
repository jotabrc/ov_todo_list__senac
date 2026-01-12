package io.github.jotabrc.ov_todo.service.task.strategy;

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
public class DeleteStrategy implements BaseStrategy<Long> {

    private final TaskMapper taskMapper;
    private final TaskRepositoryInterface taskRepository;

    @Override
    public Boolean execute(Long id) {
        return taskRepository.delete(id);
    }

    @Override
    public StrategyCommand supports() {
        return StrategyCommand.DELETE;
    }
}
