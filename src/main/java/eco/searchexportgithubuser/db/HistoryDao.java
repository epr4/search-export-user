package eco.searchexportgithubuser.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface HistoryDao extends JpaRepository<History, Date> {
}
