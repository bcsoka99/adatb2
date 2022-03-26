package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Szobafoglalas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer ejszakak;

    @ManyToOne
    @JoinColumn(name = "utasok_id")
    Utasok utasok;
    @ManyToOne
    @JoinColumn(name = "szallodak_id")
    Szallodak szallodak;
}
