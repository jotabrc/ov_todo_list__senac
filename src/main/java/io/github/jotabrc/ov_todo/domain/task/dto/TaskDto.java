package io.github.jotabrc.ov_todo.domain.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jotabrc.ov_todo.domain.task.Status;
import lombok.*;

import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private Long id;
    private String name;
    private Status status;
    private List<CategoryDto> categories;

    @JsonIgnore
    public boolean hasCategories() {
        return nonNull(this.categories) && !this.categories.isEmpty();
    }
}
