package eco.searchexportgithubuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args)
//            throws DocumentException, IOException
    {

//        new PdfTryer().tryPdf();

        SpringApplication.run(Application.class, args);
    }


}