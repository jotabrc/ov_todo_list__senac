package io.github.jotabrc.ov_todo.domain.task.entity;

import io.github.jotabrc.ov_todo.domain.task.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (isNull(status)) throw new IllegalStateException("Null or blank Status cannot be converted to String");
        return status.name().toLowerCase();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        if (isNull(status) || status.isBlank()) throw new IllegalStateException("Null or blank String cannot be converted to enum");
        return Status.valueOf(status.toUpperCase());
    }
}