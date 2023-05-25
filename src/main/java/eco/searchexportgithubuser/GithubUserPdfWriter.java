package eco.searchexportgithubuser;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


public class GithubUserPdfWriter {

//    String raw;
    List<GithubUser> githubUsers;

    public GithubUserPdfWriter(List<GithubUser> userList) {
        githubUsers=userList;
    }
/*
    public GithubUserPdfWriter(String raw) {
        this.raw=raw;
    }
*/


    public void writeToPdf(String fileName, String query) throws DocumentException, IOException {

//        raw2UserList();

        Document document = new Document();
        String folder="pdfs/";
        folder="";
        PdfWriter.getInstance(document, new FileOutputStream(folder+fileName));

        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
        Chunk chunk = new Chunk(query, font);
        document.add(chunk);
/*
        Chunk chunk2 = new Chunk("-");
        document.add(chunk2);
*/

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);

        for (GithubUser githubUser : githubUsers) {
            addRow(table,githubUser);
        }

        addCustomRows(table);

        document.add(table);
        document.close();
    }


    private void addRow(PdfPTable table, GithubUser githubUser) {
        table.addCell(githubUser.login);
        table.addCell(String.valueOf(githubUser.id));

//        table.addCell(githubUser.nodeId);
        Font fontH1 = new Font(Font.FontFamily.COURIER, 7, Font.NORMAL);
        table.addCell(new PdfPCell(new Phrase(githubUser.nodeId,fontH1)));
    }


    private void addRow(PdfPTable table, int row) {
        table.addCell("row "+row+", col 1");
        table.addCell("row "+row+", col 2");
        table.addCell("row "+row+", col 3");
    }

    private void addTableHeader(PdfPTable table) {
//        Stream.of("column header 1", "column header 2", "column header 3")
        Stream.of("Login", "ID", "Node ID")
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

        PdfPCell middleAlignCell = new PdfPCell(new Phrase("row 2, col 1"));
        middleAlignCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(middleAlignCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

/*
        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
*/

        Font fontH1 = new Font(Font.FontFamily.COURIER, 16, Font.NORMAL);
        table.addCell(new PdfPCell(new Phrase("col 3",fontH1)));

    }

}
