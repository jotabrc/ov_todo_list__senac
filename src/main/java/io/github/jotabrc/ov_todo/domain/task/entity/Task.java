package io.github.jotabrc.ov_todo.domain.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jotabrc.ov_todo.domain.task.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Category> categories;

    @JsonIgnore
    public boolean hasCategories() {
        return nonNull(this.categories) && !this.categories.isEmpty();
    }
}
