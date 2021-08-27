package pl.samples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import pl.arcyro.otp.lotos.api.dto.TimetableFinishDTO;
import pl.arcyro.otp.lotos.api.dto.TimetableLotosDTO;
import pl.arcyro.otp.lotos.model.TimetableChamberLotos;
import pl.arcyro.otp.lotos.model.TimetableLoadingJoin;
import pl.arcyro.otp.lotos.model.TimetableLotos;
import pl.arcyro.otp.lotos.model.TimetableStatusLotos;
import pl.arcyro.otp.lotos.repository.TimetableLoadingJoinRepository;
import pl.arcyro.otp.lotos.repository.TimetableLotosRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TimeTableLotosService {

    private final TimetableLotosRepository timetableLotosRepository;
    private final TimetableLoadingJoinRepository loadingJoinRepository;

    public TimeTableLotosService(TimetableLotosRepository timetableLotosRepository, TimetableLoadingJoinRepository loadingJoinRepository) {
        this.timetableLotosRepository = timetableLotosRepository;
        this.loadingJoinRepository = loadingJoinRepository;
    }

    public void changeStatus(long id, TimetableStatusLotos statusLotos) {
        timetableLotosRepository.findById(id).ifPresent(
                el -> {
                    el.setStatus(statusLotos);
                    timetableLotosRepository.save(el);
                }

        );
    }

    /**
     * @return
     */
    public List<TimetableLotosDTO> getAllByDay(Example<TimetableLotos> example) {
        return timetableLotosRepository
                .findAll(example)
                .stream()
                .map(this::toTimeTableLotosDTO)
                .collect(Collectors.toList());
    }

    private TimetableLotosDTO toTimeTableLotosDTO(TimetableLotos timetableLotos) {
        TimetableLotosDTO build = TimetableLotosDTO.builder()
                .deliveryNumber(timetableLotos.getDeliveryNumber())
                .timetableId(timetableLotos.getId())
                .loadingDate(timetableLotos.getLoadingDate())
                .loadingFrom(timetableLotos.getLoadingFrom())
                .loadingTo(timetableLotos.getLoadingTo())
                .unloadingDate(timetableLotos.getUnloadingDate())
                .unloadingFrom(timetableLotos.getUnloadingFrom())
                .unloadingTo(timetableLotos.getUnloadingTo())
                .courseNumber(timetableLotos.getCourseNumber())
                .carLicencePlate(timetableLotos.getCarLicencePlate())
                .customer(String.join(" ", timetableLotos.getCustomerLotos().getNumber().toString(),
                        timetableLotos.getCustomerLotos().getName(),
                        timetableLotos.getCustomerLotos().getAddress()))
                .loadingBase(timetableLotos.getLoadingBase().getInfo())
                .driver(timetableLotos.getUserLotos().getInfo())
                .driverNumber(timetableLotos.getUserLotos().getUserId().toString())
                .trailerLicencePlate(timetableLotos.getTrailerLicencePlate())
                .salesDocumentGL(timetableLotos.getSalesDocumentGL())
                .numberSENT(timetableLotos.getNumberSENT())
                .orderNumber(timetableLotos.getOrderNumber())
                .status(timetableLotos.getStatus().toString())
                .build();

        build.setChambers(timetableLotos.getTimetableChamberLotos()
                .stream().sorted(Comparator.comparing(TimetableChamberLotos::getChamberNumber))
                .map(TimetableMapper::toTimeTableChamberLotosDTO)
                .collect(Collectors.toList()));
        return build;
    }


    public void delete(long id) {
        timetableLotosRepository.deleteById(id);
    }

    public Optional<TimetableLotos> findById(long id) {
        return timetableLotosRepository.findById(id);
    }

    public TimetableLotosDTO findActualByUserId(TimetableStatusLotos status, Long id) {

        return timetableLotosRepository
                .findAllExcludeByStatusAndUserId(status, id)
                .stream()
                .map(this::toTimeTableLotosDTO)
                .collect(Collectors.toList()).get(0);

    }

    public void finishCourse(TimetableFinishDTO finish) {
        Optional<TimetableLoadingJoin> byId = loadingJoinRepository.findById(finish.getTimetableJoinId());
        byId.ifPresent(
                tlj -> tlj.getTimetableLotos().forEach(t -> {
                            t.setFinishCourse(finish.getFinishCourse());
                            t.setStartCourse(finish.getStartCourse());
                            t.setNumberOfKilometers(finish.getNumberOfKilometers());
                            t.setStatus(TimetableStatusLotos.FINISH);
                            timetableLotosRepository.save(t);
                        }
                )
        );
    }

    public void changeStatusByJoin(Long timetableJoinId, TimetableStatusLotos status) {
        loadingJoinRepository.updateStatus(timetableJoinId, status);
    }

    public void changeStatusByTimetableIds(List<Long> collect1, TimetableStatusLotos status) {
        loadingJoinRepository.updateStatus(collect1, status);
    }

    public void deleteByIds(String ids) {
        loadingJoinRepository.deleteByIds(Arrays.stream(ids.split(","))
                .mapToLong(Long::parseLong)
                .toArray());
    }
}
