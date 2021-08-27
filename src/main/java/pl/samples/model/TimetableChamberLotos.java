package pl.samples.model;


import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "timetable_chamber_lotos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimetableChamberLotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TimetableLotos timetableLotos;
    private String materialName;
    private Integer chamberNumber;
    private Integer tankNumber;
    private Integer quantity;
    @OneToMany(mappedBy = "timetableChamberLotos")
    private List<pl.arcyro.otp.lotos.model.TimetableLoadingLotos> loadingLotos;
    @OneToMany(mappedBy = "timetableChamberLotos")
    private List<TimetableUnloadingLotos> unloadingLotos;

}
