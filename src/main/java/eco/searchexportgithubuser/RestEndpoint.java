package eco.searchexportgithubuser;

import com.itextpdf.text.DocumentException;
import eco.searchexportgithubuser.db.History;
import eco.searchexportgithubuser.db.HistoryDao;
import eco.searchexportgithubuser.service.SearchAndExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
public class RestEndpoint {

    @Autowired
    SearchAndExportService searchAndExportService;

    @Autowired
    HistoryDao historyDao;

    @Value("${folder}")
    private String folder;


    @GetMapping("/export")
    public String exportPdf(@RequestParam String q) throws DocumentException, IOException {

//        CompletableFuture<String> response =
                searchAndExportService.searchAndExport(q);

//        return response;
        return "Searching and Exporting";
    }


    @GetMapping("/history")
    public List<History> listHistory() {
//        return historyDao.findAll();
        return historyDao.findByOrderByDateTimeDesc();
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileName") String fileName) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil(folder);

        Resource resource;
        try {
            resource = downloadUtil.getFileAsResource(fileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }


}
