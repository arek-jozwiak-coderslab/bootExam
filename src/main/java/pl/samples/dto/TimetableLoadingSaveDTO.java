package pl.samples.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class TimetableLoadingSaveDTO {
    private Long timetableJoinId;
    private LocalDateTime startLoading;
    private LocalDateTime finishLoading;

    private List<TimetableLoadingLotosDTO> items;
}
