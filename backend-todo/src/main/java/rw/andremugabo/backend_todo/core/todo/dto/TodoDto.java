package rw.andremugabo.backend_todo.core.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private UUID id;
    private String title;
    private String description;
    private boolean completed;
    private boolean active;
}
