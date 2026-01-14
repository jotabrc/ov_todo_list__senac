package io.github.jotabrc.ov_todo.mapper;

import io.github.jotabrc.ov_todo.domain.task.dto.CategoryDto;
import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import io.github.jotabrc.ov_todo.domain.task.entity.Category;
import io.github.jotabrc.ov_todo.domain.task.entity.Task;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class TaskMapper {

    public TaskDto toTaskDto(Task task) {
        if (isNull(task)) return null;
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .status(task.getStatus())
                .categories(task.hasCategories() ? task.getCategories().stream().map(this::toCategoryDto).toList() : null)
                .build();
    }

    public CategoryDto toCategoryDto(Category category) {
        if (isNull(category)) return null;
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .tasks(null)
                .build();
    }

    public Task toTask(TaskDto taskDto) {
        if (isNull(taskDto)) return null;
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .status(taskDto.getStatus())
                .categories(taskDto.hasCategories() ? taskDto.getCategories().stream().map(this::toCategory).collect(Collectors.toSet()) : null)
                .build();
    }

    public Category toCategory(CategoryDto categoryDto) {
        if (isNull(categoryDto)) return null;
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .task(null)
                .build();
    }
}