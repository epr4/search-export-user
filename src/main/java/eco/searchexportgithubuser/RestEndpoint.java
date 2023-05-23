package eco.searchexportgithubuser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestEndpoint {

    @GetMapping("/{token}")
    public String homePage(@PathVariable String token) {
        return "hello "+token;
    }

}
