package pl.samples.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Data
public class TimetableLotosDTO {

    private Long timetableId;
    private LocalDate loadingDate;
    private LocalTime loadingFrom;
    private LocalTime loadingTo;
    private LocalDate unloadingDate;
    private LocalTime unloadingFrom;
    private LocalTime unloadingTo;
    private String deliveryNumber;
    private Integer courseNumber;
    private String carLicencePlate;
    private String trailerLicencePlate;
    private String driver;
    private String driverNumber;
    private String orderNumber;
    private String salesDocumentGL;
    private String numberSENT;
    private String loadingBase;
    private String customer;
    private String status;
    private List<TimetableChamberLotosDTO> chambers;
}
