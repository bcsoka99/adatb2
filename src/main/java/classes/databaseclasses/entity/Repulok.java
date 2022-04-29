package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "REPULOK")
public class Repulok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer ferohely;
    String tipus;

    @ManyToOne
    @JoinColumn(name = "repulojaratok_id")
    Repulojaratok repulojaratok;
}
