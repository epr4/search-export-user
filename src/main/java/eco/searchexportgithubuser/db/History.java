package eco.searchexportgithubuser.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.util.Date;


@Entity
@Data
public class History {

    @Id
    Date dateTime = new Date();

    String query;

}
