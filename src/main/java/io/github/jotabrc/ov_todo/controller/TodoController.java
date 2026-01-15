package io.github.jotabrc.ov_todo.controller;

import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import io.github.jotabrc.ov_todo.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TodoController {

    private final TaskService taskService;

    @GetMapping
    public String findAll(Model model) {

        Page<TaskDto> tasks = taskService.findAll(PageRequest.of(0, 10));
        model.addAttribute("tasks", tasks.getContent());
        model.addAttribute("newTask", new TaskDto());
        return "tasks";
    }

    @GetMapping("{id}")
    public String findTaskById(@PathVariable("id") Long id, Model model) {

        TaskDto task = taskService.findById(id);
        model.addAttribute("tasks", List.of(task));
        model.addAttribute("newTask", new TaskDto());
        return "tasks";
    }

    @PostMapping
    public String save(@ModelAttribute TaskDto task, Model model) {
        Long id = taskService.save(task).getId();

        model.addAttribute("newTask", new TaskDto());
        return "redirect:tasks/%d".formatted(id);
    }
}
