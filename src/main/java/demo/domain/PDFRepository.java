package demo.domain;

import demo.domain.PDFInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PDFRepository extends JpaRepository<PDFInfo, String> {
    List<PDFInfo> findAll();
    PDFInfo findByName(@Param("name") String name);

}
