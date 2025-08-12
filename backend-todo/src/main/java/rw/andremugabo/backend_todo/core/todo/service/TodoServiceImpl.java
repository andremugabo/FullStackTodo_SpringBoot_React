package rw.andremugabo.backend_todo.core.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rw.andremugabo.backend_todo.core.todo.dto.TodoDto;
import rw.andremugabo.backend_todo.core.todo.model.Todo;
import rw.andremugabo.backend_todo.core.todo.repository.TodoRepository;
import rw.andremugabo.backend_todo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        // Todo Jpa entity
       Todo savedTodo =  todoRepository.save(todo);
       // Convert saved Todo Jpa entity object into TodoDto object
        return modelMapper.map(savedTodo, TodoDto.class );
    }

    @Override
    public TodoDto getTodo(UUID id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos =  todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto update(TodoDto todoDto, UUID id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id"+ id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto completeTodo(UUID id) {
        Todo theComplete = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id " + id));
        theComplete.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(theComplete);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto softDelete(UUID id) {
        Todo deletedTodo = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id " + id));
        deletedTodo.setActive(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(deletedTodo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(UUID id) {
        Todo inCompleteTodo = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id " + id));
        inCompleteTodo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(inCompleteTodo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

}
