package io.github.jotabrc.ov_todo.domain.task.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;
    private String name;
    private List<TaskDto> tasks;
}
