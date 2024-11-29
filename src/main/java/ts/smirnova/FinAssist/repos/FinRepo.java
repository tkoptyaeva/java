package ts.smirnova.FinAssist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ts.smirnova.FinAssist.domain.Finance;

import java.util.List;

public interface FinRepo extends JpaRepository<Finance, Long> {
    // интерфейс для связи объекта Finance с базой данных.
    // класс наследует методы из стандартного класса JpaRepository, в котором уже есть описание методов
    // для поиска объекта в бд. Но тут задан свой метод для выборки записей по конкретному пользователю

    @Query("SELECT fn FROM finances fn WHERE fn.user_id = :user_id")
    List<Finance> findByUserId(@Param("user_id") Integer user_id);
}
