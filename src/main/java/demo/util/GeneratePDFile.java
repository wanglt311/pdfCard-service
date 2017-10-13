package demo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import demo.domain.PDFInfo;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePDFile {
    public static ByteArrayInputStream PDFReport(List<PDFInfo> pdfInfoList) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(60);
            //table.setWidths(new int[]{1, 3});

            //Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            //PdfPCell hcell;
//            hcell = new PdfPCell(new Phrase("Name: ", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);

//            hcell = new PdfPCell(new Phrase("Address: ", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);

//            hcell = new PdfPCell(new Phrase("City: ", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);

//            hcell = new PdfPCell(new Phrase("StateZip: ", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);


            for (PDFInfo pdfInfo : pdfInfoList) {

                PdfPCell cell;
                //name
                cell = new PdfPCell(new Phrase("Name: " + pdfInfo.getName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                //address
                cell = new PdfPCell(new Phrase("Address: " + pdfInfo.getAddress()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                //city
                cell = new PdfPCell(new Phrase("City: " + pdfInfo.getCity()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                //stateZip
                cell = new PdfPCell(new Phrase("StateZip: " + pdfInfo.getStateZip()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, new FileOutputStream(new File("/Users/wangltlily311/TigerTech/pdfCard-service/src/main/resources/GeneratedPDF/cardInfo.pdf")));
            document.open();

            Rectangle rectangle = new Rectangle(table.getTotalWidth(), table.getTotalHeight());
            document.setPageSize(rectangle);
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePDFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
