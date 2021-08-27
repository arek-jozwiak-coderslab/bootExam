package pl.samples.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimetableFinishDTO {
    private Long timetableJoinId;
    private LocalDateTime startCourse;
    private LocalDateTime finishCourse;
    private Long numberOfKilometers;
}
