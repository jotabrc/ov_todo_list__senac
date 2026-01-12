package io.github.jotabrc.ov_todo.service.task.executor;

import io.github.jotabrc.ov_todo.service.BaseStrategy;
import io.github.jotabrc.ov_todo.service.StrategyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskExecutorImpl implements TaskExecutor {

    private final TaskResolver taskResolver;

    @Override
    public <T, R> R execute(T t, Class<R> r, StrategyCommand strategyCommand) {
        return ((BaseStrategy<T, R>) taskResolver.getOrElseThrow(strategyCommand))
                .execute(t);
    }
}
