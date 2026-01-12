package io.github.jotabrc.ov_todo.service.task.executor;

import io.github.jotabrc.ov_todo.handler.OvException;
import io.github.jotabrc.ov_todo.service.BaseStrategy;
import io.github.jotabrc.ov_todo.service.StrategyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class TaskResolver {

    private final Map<StrategyCommand, BaseStrategy<?, ?>> strategies = new HashMap<>();

    @Autowired
    public TaskResolver(List<BaseStrategy<?, ?>> strategies) {
        strategies.forEach(strategy -> this.strategies.putIfAbsent(strategy.supports(), strategy));
    }

    public BaseStrategy<?, ?> getOrElseThrow(StrategyCommand strategyCommand) {
        return Optional.ofNullable(this.strategies.get(strategyCommand))
                .orElseThrow(() -> new OvException.StrategyNotFound("Strategy of type %s not found".formatted(strategyCommand.name())));
    }
}
