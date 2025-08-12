package rw.andremugabo.backend_todo.core.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.andremugabo.backend_todo.core.todo.model.Todo;

import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
}
