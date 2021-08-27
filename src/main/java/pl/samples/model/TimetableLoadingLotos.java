package pl.samples.model;


import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
@Table(name = "timetable_chamber_lotos_loading")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimetableLoadingLotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer wage;
    private Integer realQuantityIn15;
    private Integer realQuantity;
    private String comments;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TimetableChamberLotos timetableChamberLotos;
    private LocalDateTime startLoading;
    private LocalDateTime finishLoading;


}
