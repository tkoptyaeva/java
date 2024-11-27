package ts.smirnova.FinAssist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.smirnova.FinAssist.domain.MyUser;

import java.util.Optional;

public interface UserRepo extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}
