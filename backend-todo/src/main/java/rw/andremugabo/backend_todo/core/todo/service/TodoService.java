package rw.andremugabo.backend_todo.core.todo.service;

import rw.andremugabo.backend_todo.core.todo.dto.TodoDto;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(UUID id);
    List<TodoDto> getAllTodos();
    TodoDto update(TodoDto todoDto, UUID id);
    TodoDto completeTodo(UUID id);
    TodoDto softDelete(UUID id);
    TodoDto inCompleteTodo(UUID id);
}
