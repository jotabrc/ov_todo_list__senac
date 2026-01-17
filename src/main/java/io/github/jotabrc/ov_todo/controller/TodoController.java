package io.github.jotabrc.ov_todo.controller;

import io.github.jotabrc.ov_todo.domain.task.Status;
import io.github.jotabrc.ov_todo.domain.task.dto.TaskDto;
import io.github.jotabrc.ov_todo.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        return "all-tasks";
    }

    @GetMapping("/new-task")
    public String newTask(Model model) {
        model.addAttribute("newTask", new TaskDto());
        return "new-task";
    }

    @GetMapping("/find")
    public String find(@RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "status", required = false) String status,
                       @PageableDefault(size = 10, page = 0) Pageable pageable,
                       Model model) {
        List<TaskDto> tasks = taskService.find(id, status, pageable);
        model.addAttribute("tasks", tasks);
        return "all-tasks";
    }

    @PostMapping
    public String save(@ModelAttribute TaskDto task, Model model) {
        Long id = taskService.save(task).getId();
        return "redirect:/tasks?id=%d".formatted(id);
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        TaskDto task = taskService.findById(id);
        model.addAttribute("updateTask", task);
        return "edit-task";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute TaskDto task, Model model) {
        Long id = taskService.update(task).getId();
        return "redirect:/tasks?id=%d".formatted(id);
    }

    @PutMapping(value = "/update/{id}/status")
    public String updateToDone(@PathVariable("id") Long id) {
        taskService.updateStatus(id, Status.DONE);
        return "redirect:/tasks";
    }
}
