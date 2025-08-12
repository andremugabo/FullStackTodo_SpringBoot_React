package rw.andremugabo.backend_todo.controller.todo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.andremugabo.backend_todo.core.todo.dto.TodoDto;
import rw.andremugabo.backend_todo.core.todo.service.TodoService;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/todos")
@Tag(name="Todo Management", description = "Operation for managing Todo")
public class TodoController {

    private final TodoService todoService;


    //Build Add Todo REST API
    @Operation(
            summary = "Create a Todo record",
            description = "Create a new todo record for a given user "
    )
    @PostMapping("/create")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    // Build Get Todo REST API
    @Operation(
            summary = "Get Todo by Id",
            description = "Get one todo by id"
    )
    @GetMapping("/getTodo/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") UUID todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }


    //Build Get All Todo REST API
    @Operation(
            summary = "Get all todos",
            description = "Get all registered todos"
    )
    @GetMapping("/getAllTodos")
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> todoDto = todoService.getAllTodos();
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Todo update REST API
    @Operation(
            summary = "Update Todo by Id",
            description = "Update the selected todo by id"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") UUID id){
        TodoDto updatedTodo = todoService.update(todoDto,id);
        return ResponseEntity.ok(updatedTodo);
    }


    // Build Todo REST API for update a completed todo
    @Operation(
            summary = "Complete a todo by Id",
            description = "Update the status of uncompleted todo to completed"
    )
    @PutMapping("/completed/{id}")
    public ResponseEntity<TodoDto> completedTodo(@PathVariable  UUID id){
        TodoDto completedTodo = todoService.completeTodo(id);
        return ResponseEntity.ok(completedTodo);
    }


    // Build Soft delete REST API
    @Operation(
            summary = "Soft delete a Todo",
            description = "Soft delete a todo change it's active status to false"
    )
    @PutMapping("/softDelete/{id}")
    public ResponseEntity<TodoDto> softDelete(@PathVariable UUID id){
        TodoDto softDeleted = todoService.softDelete(id);
        return ResponseEntity.ok(softDeleted);
    }
    @Operation(
            summary = "Incomplete a todo",
            description = "Incomplete todo bring back the complete status to false "
    )
    @PatchMapping("/inCompleted/{id}")
    public ResponseEntity<TodoDto> inCompleted(@PathVariable UUID id){
        TodoDto inCompleteTodo = todoService.inCompleteTodo(id);
        return  ResponseEntity.ok(inCompleteTodo);
    }


}
