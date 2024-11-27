package ts.smirnova.FinAssist.repos;

import org.springframework.data.repository.CrudRepository;
import ts.smirnova.FinAssist.domain.Finance;

public interface FinRepo extends CrudRepository<Finance, Long> {

}
