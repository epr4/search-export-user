package eco.searchexportgithubuser;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;


@RestController
public class RestEndpoint {

    @GetMapping("/{token}")
    public List<GithubUser> homePage(@PathVariable String token) {

        RestTemplate restTemplate = new RestTemplate();
        URI uri=URI.create("https://api.github.com/search/users?q=Q");
        ResponseEntity<List<GithubUser>> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });

        List<GithubUser> result = response.getBody();

//        return "hello "+token;
        return result;
    }

}
