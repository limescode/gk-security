package pl.limescode.gksecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.limescode.gksecurity.entity.Privilege;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
}
