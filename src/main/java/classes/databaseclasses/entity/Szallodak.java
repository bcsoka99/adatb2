package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Szallodak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nev;
    String kategoria;
    String telepules;
    Integer ar;

    @OneToMany(mappedBy = "szallodak")
    List<Szobafoglalas> szobafoglalas;
}
