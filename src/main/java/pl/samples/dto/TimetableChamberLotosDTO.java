package pl.samples.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TimetableChamberLotosDTO {
    private String materialName;
    private Long timetableId;
    private String customer;
    private Integer chamberNumber;
    private Integer tankNumber;
    private Integer quantity;
    private Long chamberId;
    private List<TimetableLoadingLotosDTO> loadings;
    private List<TimetableUnloadingLotosDTO> unloadings;
}
