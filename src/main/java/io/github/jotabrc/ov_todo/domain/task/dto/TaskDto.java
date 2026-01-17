package io.github.jotabrc.ov_todo.domain.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.service.validation.ValidateName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private Long id;
    @ValidateName
    private String name;
    @NotNull
    @Builder.Default
    private Status status = Status.PENDING;
    @Valid
    @Builder.Default
    private List<CategoryDto> categories = new ArrayList<>();

    @JsonIgnore
    public boolean hasCategories() {
        return nonNull(this.categories) && !this.categories.isEmpty();
    }

    @JsonIgnore
    public String getCategoryInput() {
        return Optional.ofNullable(this.categories)
                .orElse(Collections.emptyList())
                .stream()
                .map(CategoryDto::getName)
                .collect(Collectors.joining(", "));
    }

    public void setCategoryInput(String input) {
        Arrays.stream(Optional.ofNullable(input)
                .orElse("")
                .split(","))
                .map(String::trim)
                .filter(str -> !str.isBlank())
                .forEach(name -> this.categories.add(CategoryDto.builder().name(name).build()));
    }

    public boolean isDone() {
        return this.status.equals(Status.DONE);
    }
}
