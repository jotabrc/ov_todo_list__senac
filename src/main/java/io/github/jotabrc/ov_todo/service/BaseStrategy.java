package io.github.jotabrc.ov_todo.service;

public interface BaseStrategy<T, R> {

    R execute(T t);
    StrategyCommand supports();
}
