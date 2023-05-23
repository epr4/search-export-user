package eco.searchexportgithubuser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


public class GithubUserPdfWriter {

    String raw;
    List<GithubUser> githubUsers;

    public GithubUserPdfWriter(String raw) {
        this.raw=raw;
    }


    public void writeToPdf() throws DocumentException, IOException {

        raw2UserList();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));

        document.open();

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);

        addRows(table);
        int row=2;
        for (;row<=64;row++) {
            addRow(table,row);
        }

        addCustomRows(table);

        document.add(table);
        document.close();
    }

    private void raw2UserList() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GithubApiResponse githubApiResponse = objectMapper.readValue(raw,GithubApiResponse.class);
        System.out.println("githubApiResponse = " + githubApiResponse);
    }

    private void addRow(PdfPTable table, int row) {
        table.addCell("row "+row+", col 1");
        table.addCell("row "+row+", col 2");
        table.addCell("row "+row+", col 3");
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private void addCustomRows(PdfPTable table)
//            throws URISyntaxException, BadElementException, IOException
    {
/*
        Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(10);

        PdfPCell imageCell = new PdfPCell(img);
        table.addCell(imageCell);
*/

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);


    }

}
