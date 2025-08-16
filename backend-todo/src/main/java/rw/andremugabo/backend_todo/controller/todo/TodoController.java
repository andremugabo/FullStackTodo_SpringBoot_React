package rw.andremugabo.backend_todo.controller.todo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rw.andremugabo.backend_todo.core.todo.dto.TodoDto;
import rw.andremugabo.backend_todo.core.todo.service.TodoService;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/todos")
@Tag(name="Todo Management", description = "Operation for managing Todo")
public class TodoController {

    private final TodoService todoService;


    //Build Add Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Create a Todo record",
            description = "Create a new todo record for a given user ",
            security = { @SecurityRequirement(name = "basicAuth")}
    )
    @PostMapping("/create")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    // Build Get Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(
            summary = "Get Todo by Id",
            description = "Get one todo by id",
            security = { @SecurityRequirement(name = "basicAuth")}
    )
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") UUID todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }


    //Build Get All Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(
            summary = "Get all todos",
            description = "Get all registered todos",
            security = { @SecurityRequirement(name = "basicAuth")}
    )
    @GetMapping("/")
    public ResponseEntity<Page<TodoDto>> getAllTodo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TodoDto> todoDtoPage = todoService.getAllTodos(pageable);
        return new ResponseEntity<>(todoDtoPage, HttpStatus.OK);
    }

    // Build Todo update REST API
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update Todo by Id",
            description = "Update the selected todo by id",
            security = { @SecurityRequirement(name = "basicAuth")}
    )
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") UUID id){
        TodoDto updatedTodo = todoService.update(todoDto,id);
        return ResponseEntity.ok(updatedTodo);
    }


    // Build Todo REST API for update a completed todo
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(
            summary = "Complete a todo by Id",
            description = "Update the status of uncompleted todo to completed",
            security = { @SecurityRequirement(name = "basicAuth")}
    )
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completedTodo(@PathVariable  UUID id){
        TodoDto completedTodo = todoService.completeTodo(id);
        return ResponseEntity.ok(completedTodo);
    }


    // Build Soft delete REST API
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(
            summary = "Soft delete a Todo",
            description = "Soft delete a todo change it's active status to false",
            security = { @SecurityRequirement(name = "basicAuth")}
    )
    @PutMapping("/{id}/soft-delete")
    public ResponseEntity<TodoDto> softDelete(@PathVariable UUID id){
        TodoDto softDeleted = todoService.softDelete(id);
        return ResponseEntity.ok(softDeleted);
    }

    // Build Incomplete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(
            summary = "Incomplete a todo",
            description = "Incomplete todo bring back the complete status to false ",
            security = { @SecurityRequirement(name = "basicAuth")}
    )
    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleted(@PathVariable UUID id){
        TodoDto inCompleteTodo = todoService.inCompleteTodo(id);
        return  ResponseEntity.ok(inCompleteTodo);
    }


}
