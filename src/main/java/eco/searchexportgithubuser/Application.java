package eco.searchexportgithubuser;

import com.itextpdf.text.DocumentException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class Application {
    public static void main(String[] args)
//            throws DocumentException, IOException
    {

//        new PdfTryer().tryPdf();

        SpringApplication.run(Application.class, args);
    }


}