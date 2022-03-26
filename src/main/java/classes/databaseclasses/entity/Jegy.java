package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Jegy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer ar;
    Date datum;

    @ManyToOne
    @JoinColumn(name = "utasok_id")
    Utasok utasok;
    @ManyToOne
    @JoinColumn(name = "repulojaratok_id")
    Repulojaratok repulojaratok;

}
