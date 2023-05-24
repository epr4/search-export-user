package eco.searchexportgithubuser.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
public class History {

    @Id
    Date dateTime = new Date();

    String query;

    String fileName;

    public History(String query) {
        this.query=query;
    }

}
