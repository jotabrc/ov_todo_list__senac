package io.github.jotabrc.ov_todo.domain.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jotabrc.ov_todo.domain.task.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "tb_task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Status status;
    @ManyToMany(mappedBy = "tasks")
    private List<Category> categories;

    @JsonIgnore
    public boolean hasCategories() {
        return nonNull(this.categories) && !this.categories.isEmpty();
    }
}
