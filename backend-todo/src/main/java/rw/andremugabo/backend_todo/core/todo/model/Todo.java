package rw.andremugabo.backend_todo.core.todo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.andremugabo.backend_todo.core.base.AbstractBaseEntity;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "todos")
public class Todo extends AbstractBaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private Boolean completed = Boolean.FALSE;
}
