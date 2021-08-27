package pl.samples.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TimetableLotosJoinDTO {
    private Long timetableJoinId;
    private String loadingDate;
    private String loadingFrom;
    private String loadingTo;
    private List<String> unloadingDateTime;
    private Integer courseNumber;
    private String carLicencePlate;
    private String trailerLicencePlate;
    private List<String> orderNumber;
    private List<String> salesDocumentGL;
    private List<String> numberSENT;
    private String loadingBase;
    private List<String> customer;
    private List<TimetableChamberLotosDTO> chambers;
}
