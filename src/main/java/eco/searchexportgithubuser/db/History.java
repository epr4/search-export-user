package eco.searchexportgithubuser.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;


@Entity
@Getter
public class History {

    @Id
    Date dateTime = new Date();

}
