package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Repulok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String tipus;
    Integer ferohely;

    @ManyToOne
    @JoinColumn(name = "repulojaratok_id")
    Repulojaratok repulojaratok;
}
