package io.github.jotabrc.ov_todo.domain.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.service.validation.ValidateName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
}
