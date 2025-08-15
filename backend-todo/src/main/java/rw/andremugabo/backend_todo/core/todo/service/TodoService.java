package rw.andremugabo.backend_todo.core.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.andremugabo.backend_todo.core.todo.dto.TodoDto;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(UUID id);
    Page<TodoDto> getAllTodos(Pageable pageable);
    TodoDto update(TodoDto todoDto, UUID id);
    TodoDto completeTodo(UUID id);
    TodoDto softDelete(UUID id);
    TodoDto inCompleteTodo(UUID id);
}
