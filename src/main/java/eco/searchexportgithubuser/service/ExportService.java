package eco.searchexportgithubuser.service;

import com.itextpdf.text.DocumentException;
import eco.searchexportgithubuser.GithubUser;
import eco.searchexportgithubuser.GithubUserPdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class ExportService {

    @Value("${folder}")
    private String folder;

    boolean export(String query, List<GithubUser> githubUsers) throws DocumentException, IOException {
        String fileName = query+".pdf";
        GithubUserPdfWriter githubUserPdfWriter = new GithubUserPdfWriter(githubUsers,folder);
        githubUserPdfWriter.writeToPdf(fileName, query);
        return true;
    }

}
