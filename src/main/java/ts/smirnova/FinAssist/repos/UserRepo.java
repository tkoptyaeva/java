package ts.smirnova.FinAssist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.smirnova.FinAssist.domain.MyUser;

import java.util.Optional;

public interface UserRepo extends JpaRepository<MyUser, Long> {
    // интерфейс для связи объекта MyUser с базой данных.
    // класс наследует методы из стандартного класса JpaRepository, в котором уже есть описание методов
    // для поиска объекта в бд. Но тут задан свой метод поиска пользователя по логину
    Optional<MyUser> findByUsername(String username);
}
