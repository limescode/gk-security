package pl.limescode.gksecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.limescode.gksecurity.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
