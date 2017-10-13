package demo.service.impl;

import demo.domain.PDFInfo;
import demo.domain.PDFRepository;
import demo.service.PDFInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDFInfoServiceImpl implements PDFInfoService{
    private PDFRepository pdfRepository;

    @Autowired
    public PDFInfoServiceImpl(PDFRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }
    @Override
    public List<PDFInfo> findAll() {
        return this.pdfRepository.findAll();
    }

    @Override
    public  PDFInfo savePDFInfo(PDFInfo pdfInfo) {
        return this.pdfRepository.save(pdfInfo);
    }
    @Override
    public List<PDFInfo> savePDFInfoList(List<PDFInfo> pdfInfoList) {
        return this.pdfRepository.save(pdfInfoList);
    }

    @Override
    public PDFInfo findPDFInfoByName(String name) {
        return this.pdfRepository.findByName(name);
    }

    @Override
    public List<PDFInfo> findAllPDFInfoOrderByName(String sortDir, String sortBy) {
        return this.pdfRepository.findAll();
    }
}
