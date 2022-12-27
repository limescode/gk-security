package pl.limescode.gksecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.limescode.gksecurity.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsername(String username);

}
