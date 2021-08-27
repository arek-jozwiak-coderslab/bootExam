package pl.samples;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.arcyro.otp.lotos.api.dto.TimetableLotosJoinDTO;
import pl.arcyro.otp.lotos.model.TimetableStatusLotos;

import java.util.List;

@RestController
@RequestMapping("/api/lotos/v1/")
@Slf4j
@AllArgsConstructor
public class LotosTimetableDriverResource {

    private final TimeTableLotosService tableLotosService;
    private final TimetableJoinService timeTableLotosService;

    @InitBinder     /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /**
     * @param userId
     * @return
     * @TODO - probably change id to hash
     */
    @GetMapping("/timetable/user/{userId}")
    public ResponseEntity<List<TimetableLotosJoinDTO>> findUnfinishedByUserId(@PathVariable("userId") Long userId)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(
                timeTableLotosService.findExcludeByStatusAndUserId(TimetableStatusLotos.FINISH, userId),
                HttpStatus.OK);
    }

    @GetMapping("/timetable/user/actual/{userId}/{status}/{actualId}")
    public ResponseEntity<TimetableLotosJoinDTO> findActualByUserId(@PathVariable Long userId,
                                                                    @PathVariable Long actualId,
                                                                    @PathVariable TimetableStatusLotos status)
            throws ResourceNotFoundException {
        TimetableLotosJoinDTO timetableLotosJoinDTO = timeTableLotosService.
                findByTimetableJoinId(actualId).get(0);
        tableLotosService.changeStatusByJoin(timetableLotosJoinDTO.getTimetableJoinId(),
                status);
        return new ResponseEntity<>(timetableLotosJoinDTO, HttpStatus.OK);
    }
}
