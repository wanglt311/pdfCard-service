package demo.controller;

import demo.domain.PDFInfo;
import demo.service.PDFInfoService;
import demo.util.GeneratePDFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
public class PDFInfoController {
    private static final String DEFAULT_SORT_DIR = "desc";
    private static final String DEFAULT_SORT_BY = "name";

    @Autowired
    private PDFInfoService pdfInfoService;

    @Autowired
    public PDFInfoController(PDFInfoService pdfInfoService) {
        this.pdfInfoService = pdfInfoService;
    }

    //produces = MediaType.APPLICATION_JSON_VALUE
    //consumes = MediaType.APPLICATION_JSON_VALUE

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloworld() {
        return "Hello World";
    }

    @RequestMapping(value = "/pdfInfos", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<PDFInfo> pdfInfoList) {
        for (PDFInfo pdf : pdfInfoList) {
            System.out.println(pdf);
        }
        this.pdfInfoService.savePDFInfoList(pdfInfoList);
    }

    @RequestMapping(value = "/pdfInfos/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> findPDFInfoByName(@PathVariable("name") String name) throws Exception {
        PDFInfo pdfInfo = this.pdfInfoService.findPDFInfoByName(name);
        if(pdfInfo == null) {
            return ResponseEntity.notFound().build();
        }
        //ByteArrayInputStream bis = GeneratePDFile.PDFReport(pdfInfo);
        return ResponseEntity.ok(pdfInfo);
    }

    //produces = MediaType.APPLICATION_JSON_VALUE
    @RequestMapping(value = "/pdfInfos", method = RequestMethod.GET)
    public List<PDFInfo> findAllRunningInfoOrderByName(@RequestParam(value = "sortDir", required = false, defaultValue = DEFAULT_SORT_DIR) String sortDir,
                                                       @RequestParam(value = "sortBy", required = false, defaultValue = DEFAULT_SORT_BY) String sortBy) {
        List<PDFInfo> pdfInfoList = this.pdfInfoService.findAllPDFInfoOrderByName(sortDir, sortBy);
        return this.pdfInfoService.findAllPDFInfoOrderByName(sortDir, sortBy);
    }

    //produces = MediaType.APPLICATION_PDF_VALUE
    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET)
    public void citiesReport() throws IOException {
        List<PDFInfo> pdfInfoList = this.pdfInfoService.findAll();
        ByteArrayInputStream bis = GeneratePDFile.PDFReport(pdfInfoList);
        try {
            String outStr=bis.toString();

            File newFile=new File("/Users/cardInfo.pdf");
            FileOutputStream fos = new FileOutputStream(newFile);
            int data;
            while((data=bis.read())!=-1)
            {
                char ch = (char)data;
                fos.write(ch);
            }
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
