package rw.andremugabo.backend_todo.core.base;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public abstract class AbstractBaseDtoEntity {
    private UUID id;
    private boolean active = Boolean.TRUE;
}
