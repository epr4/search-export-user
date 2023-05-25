package eco.searchexportgithubuser;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchService {

    @Cacheable("users")
    String search(String query) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "https://api.github.com/search/users?q="+ query;
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl, String.class);
        return response.getBody();
    }


}
