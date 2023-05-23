package eco.searchexportgithubuser;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Setter;

import java.util.List;


@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GithubApiResponse {

    int totalCount;
    boolean incompleteResults;

    List<GithubUser> items;

}
