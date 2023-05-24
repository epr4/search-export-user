package eco.searchexportgithubuser;

import com.itextpdf.text.DocumentException;
import eco.searchexportgithubuser.db.History;
import eco.searchexportgithubuser.db.HistoryDao;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
public class RestEndpoint {

    @Autowired
    HistoryDao historyDao;

    @GetMapping("/export/{query}")
    public ResponseEntity<String> exportPdf(@PathVariable String query) throws DocumentException, IOException {

        History history = new History();
        history.setQuery(query);
        historyDao.save(history);

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

    @GetMapping("/history")
    public List<History> listHistory() {
        return historyDao.findAll();
    }

    @GetMapping(value = "/donwload", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] donwload() throws IOException {
/*
        InputStream in = getClass()
//                .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
                .getResourceAsStream("iTextTable.pdf");
*/
        String fileName = "iTextTable.pdf";
        InputStream in = new FileInputStream(fileName);
        return IOUtils.toByteArray(in);

/*
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + fileName + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
*/
    }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
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
