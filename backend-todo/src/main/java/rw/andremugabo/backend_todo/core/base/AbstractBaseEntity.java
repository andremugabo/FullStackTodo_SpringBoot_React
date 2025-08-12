package rw.andremugabo.backend_todo.core.base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Getter @Setter
@MappedSuperclass
public abstract class AbstractBaseEntity extends AbstractAuditEntity{
    @Id
    @UuidGenerator
    private UUID id;
    @Column(name = "active", nullable = false)
    private boolean active = Boolean.TRUE;
}
