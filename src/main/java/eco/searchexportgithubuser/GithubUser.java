package eco.searchexportgithubuser;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Setter;


@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GithubUser {

    String login;
    int id;
    String nodeId;

}
