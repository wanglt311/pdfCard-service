package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//import org.projectlombok;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "PDF_INFO")
@Data
public class PDFInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String stateZip;

    public PDFInfo() {}

    @JsonCreator
    public PDFInfo(@JsonProperty("name") String name,
                   @JsonProperty("address") String address,
                   @JsonProperty("city") String city,
                   @JsonProperty("stateZip") String stateZip) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.stateZip = stateZip;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public void setStateZip(String stateZip) {
//        this.stateZip = stateZip;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public String getAddress() {
//        return this.address;
//    }
//
//    public String getCity() {
//        return this.city;
//    }
//
//    public String getStateZip() {
//        return this.stateZip;
//    }

    @Override
    public String toString() {
        return "PDFCard{" + "name=" + name + ", address=" + address
                + ", city=" + city + ", stateZip=" + stateZip +'}';
    }
}
