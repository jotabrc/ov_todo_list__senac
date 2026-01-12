package io.github.jotabrc.ov_todo.service.task.executor;

import io.github.jotabrc.ov_todo.service.StrategyCommand;

public interface TaskExecutor {

    <T, R> R execute(T t, Class<R> r, StrategyCommand strategyCommand);
}
