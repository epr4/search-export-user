package eco.searchexportgithubuser;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@SpringBootApplication
public class Application {
    public static void main(String[] args) throws DocumentException, FileNotFoundException {

        tryPdf();

//        SpringApplication.run(Application.class, args);
    }

    private static void tryPdf() throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World", font);

        document.add(chunk);
        document.close();
    }

}