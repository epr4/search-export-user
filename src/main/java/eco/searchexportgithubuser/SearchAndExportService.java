package eco.searchexportgithubuser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import eco.searchexportgithubuser.db.History;
import eco.searchexportgithubuser.db.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class SearchAndExportService {

    @Autowired
    HistoryDao historyDao;

    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity<String>> searchAndExport(String query) throws DocumentException, IOException {
        String fileName = "iText-Table.pdf";
        fileName=query+".pdf";

        History history = new History(query);
//        history.setFileName(fileName);

        history.setStatus(Status.SEARCHING);
        historyDao.save(history);
        ResponseEntity<String> response = search(query);

        history.setStatus(Status.EXPORTING);
        historyDao.save(history);
        GithubUserPdfWriter githubUserPdfWriter = new GithubUserPdfWriter(raw2UserList(response.getBody()));
        githubUserPdfWriter.writeToPdf(fileName, query);
        history.setStatus(Status.DONE);
        history.setFileName(fileName);
        historyDao.save(history);
        return CompletableFuture.completedFuture(response);
    }

    @Cacheable("users")
    private static ResponseEntity<String> search(String query) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "https://api.github.com/search/users?q="+ query;
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl, String.class);
        return response;
    }

    private List<GithubUser> raw2UserList(String raw) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GithubApiResponse githubApiResponse = objectMapper.readValue(raw,GithubApiResponse.class);
        return githubApiResponse.getItems();
    }


}
