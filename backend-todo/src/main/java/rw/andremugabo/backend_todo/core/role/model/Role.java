package rw.andremugabo.backend_todo.core.role.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.andremugabo.backend_todo.core.base.AbstractBaseEntity;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AbstractBaseEntity {
    private String name;
}
