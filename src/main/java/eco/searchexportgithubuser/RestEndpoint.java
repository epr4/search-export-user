package eco.searchexportgithubuser;

import com.itextpdf.text.DocumentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController
public class RestEndpoint {

    @GetMapping("/{query}")
    public ResponseEntity<String> homePage(@PathVariable String query) throws DocumentException, IOException {

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "https://api.github.com/search/users?q="+query;
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl, String.class);

        GithubUserPdfWriter githubUserPdfWriter = new GithubUserPdfWriter(response.getBody());
        githubUserPdfWriter.writeToPdf();

//        return "hello "+token;
        return response;
    }

}
