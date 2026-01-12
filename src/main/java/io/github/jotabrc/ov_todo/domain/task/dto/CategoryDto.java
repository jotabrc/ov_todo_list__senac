package io.github.jotabrc.ov_todo.domain.task.dto;

import io.github.jotabrc.ov_todo.service.validation.ValidateName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;
    @ValidateName
    private String name;
    @Builder.Default
    private List<TaskDto> tasks = new ArrayList<>();
}
