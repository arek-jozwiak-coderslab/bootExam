package pl.samples.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TimetableUnloadingLotosDTO {

    private Long unloadingId;
    private Long timetableId;
    private Long timetableChamberLotosId;
    private Integer wage;
    private Integer realQuantityIn15;
    private Integer realQuantity;
    private String comments;

}
