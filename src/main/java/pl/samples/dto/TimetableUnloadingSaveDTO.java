package pl.samples.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class TimetableUnloadingSaveDTO {
    private Long timetableJoinId;
    private LocalDateTime startUnloading;
    private LocalDateTime finishUnloading;

    private List<TimetableUnloadingLotosDTO> items;
}
