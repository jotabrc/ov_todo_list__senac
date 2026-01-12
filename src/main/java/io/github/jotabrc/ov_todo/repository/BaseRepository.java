package io.github.jotabrc.ov_todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T, U> extends JpaRepository<T, U> {
}
