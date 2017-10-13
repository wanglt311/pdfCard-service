package demo.service;

import demo.domain.PDFInfo;

import java.util.List;

public interface PDFInfoService {
    public List<PDFInfo> findAll();

    PDFInfo savePDFInfo(PDFInfo pdfInfo);

    List<PDFInfo> savePDFInfoList(List<PDFInfo> pdfInfoList);

    PDFInfo findPDFInfoByName(String name);

    List<PDFInfo> findAllPDFInfoOrderByName(String sortDir, String sortBy);
}
