package eco.searchexportgithubuser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RestEndpoint {

    @GetMapping("/{token}")
    public ResponseEntity<String> homePage(@PathVariable String token) {

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "https://api.github.com/search/users?q=Q";
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl, String.class);

//        return "hello "+token;
        return response;
    }

}
