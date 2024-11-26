package ts.smirnova.FinAssist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.smirnova.FinAssist.domain.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
//    User findByUsername(String username);

    Optional<User> findByUsername(String username);
}
