package eco.searchexportgithubuser.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HistoryDao extends JpaRepository<History, Date> {
    List<History> findByOrderByDateTimeDesc();
}
