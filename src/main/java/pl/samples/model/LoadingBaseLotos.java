package pl.samples.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "timetable_lotos_bases")
@Builder
@AllArgsConstructor
@NoArgsConstructor
//0006000589 ZR49 MODLIN
public class LoadingBaseLotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstCode; //0006000589
    private String secondCode; //ZR49
    private String name; //MODLIN


    public String getInfo() {
        return String.join(" ", firstCode, secondCode, name);
    }
}
