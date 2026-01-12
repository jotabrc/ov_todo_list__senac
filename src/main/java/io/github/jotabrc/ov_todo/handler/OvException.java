package io.github.jotabrc.ov_todo.handler;

public class OvException extends RuntimeException {
    public OvException(String message) {
        super(message);
    }

    public static class EntityNotFound extends OvException {
        public EntityNotFound(String message) {
            super(message);
        }
    }

    public static class StrategyNotFound extends OvException {
        public StrategyNotFound(String message) {
            super(message);
        }
    }
}
