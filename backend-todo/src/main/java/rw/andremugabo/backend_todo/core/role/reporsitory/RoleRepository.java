package rw.andremugabo.backend_todo.core.role.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.andremugabo.backend_todo.core.role.model.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
