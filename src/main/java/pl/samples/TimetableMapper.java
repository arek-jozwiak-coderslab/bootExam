package pl.samples;

import pl.arcyro.otp.lotos.api.dto.TimetableChamberLotosDTO;
import pl.arcyro.otp.lotos.api.dto.TimetableLoadingLotosDTO;
import pl.arcyro.otp.lotos.api.dto.TimetableUnloadingLotosDTO;
import pl.arcyro.otp.lotos.model.TimetableChamberLotos;

import java.util.stream.Collectors;

public class TimetableMapper {

    public static TimetableChamberLotosDTO toTimeTableChamberLotosDTO(TimetableChamberLotos timetableChamberLotos) {
        return TimetableChamberLotosDTO
                .builder()
                .chamberNumber(timetableChamberLotos.getChamberNumber())
                .materialName(timetableChamberLotos.getMaterialName())
                .quantity(timetableChamberLotos.getQuantity())
                .tankNumber(timetableChamberLotos.getTankNumber())
                .chamberId(timetableChamberLotos.getId())
                .timetableId(timetableChamberLotos.getTimetableLotos().getId())
                .customer(
                        String.join(" ",
                                timetableChamberLotos.getTimetableLotos().getCustomerLotos().getAddress()
                ))
                .loadings(timetableChamberLotos.getLoadingLotos()
                        .stream()
                        .map(tll -> TimetableLoadingLotosDTO.builder()
                                .loadingId(tll.getId())
                                .realQuantity(tll.getRealQuantity())
                                .realQuantityIn15(tll.getRealQuantityIn15())
                                .wage(tll.getWage())
                                .comments(tll.getComments())
                                .build()
                        ).collect(Collectors.toList()))
                .unloadings(timetableChamberLotos.getUnloadingLotos()
                        .stream()
                        .map(tul-> TimetableUnloadingLotosDTO.builder()
                                .unloadingId(tul.getId())
                                .realQuantity(tul.getRealQuantity())
                                .realQuantityIn15(tul.getRealQuantityIn15())
                                .comments(tul.getComments())
                                .build()
                        ).collect(Collectors.toList())

                )
                .build();
    }
}
