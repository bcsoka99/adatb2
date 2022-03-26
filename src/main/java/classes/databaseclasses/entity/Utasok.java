package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utasok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nev;
    Date szuletesiDatum;

    @OneToMany(mappedBy = "utasok")
    List<Biztositasok> biztositasok;
    @OneToMany(mappedBy = "utasok")
    List<Szobafoglalas> szobafoglalas;
    @OneToMany(mappedBy = "utasok")
    List<Jegy> jegy;
}
