package eco.searchexportgithubuser.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import eco.searchexportgithubuser.GithubApiResponse;
import eco.searchexportgithubuser.GithubUser;
import eco.searchexportgithubuser.Status;
import eco.searchexportgithubuser.db.History;
import eco.searchexportgithubuser.db.HistoryDao;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class SearchAndExportService {

    SearchService searchService;
    ExportService exportService;

//    @Autowired
    HistoryDao historyDao;


    public SearchAndExportService(SearchService searchService, ExportService exportService, HistoryDao historyDao) {
        this.searchService = searchService;
        this.exportService = exportService;
        this.historyDao = historyDao;
    }


    @Async("asyncExecutor")
    public CompletableFuture<List<GithubUser>> searchAndExport(String query) throws DocumentException, IOException {

        History history = new History(query);
//        history.setFileName(fileName);

        history.setStatus(Status.SEARCHING);
        historyDao.save(history);
        List<GithubUser> githubUsers = search(query);

        history.setStatus(Status.EXPORTING);
        historyDao.save(history);
        exportService.export(query, githubUsers);
        history.setStatus(Status.DONE);
        String fileName = query+".pdf";
        history.setFileName(fileName);
        historyDao.save(history);
        return CompletableFuture.completedFuture(githubUsers);
    }


    List<GithubUser> search(String query) throws JsonProcessingException {
        String json = searchService.search(query);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GithubApiResponse githubApiResponse = objectMapper.readValue(json,GithubApiResponse.class);
        return githubApiResponse.getItems();
    }


}
