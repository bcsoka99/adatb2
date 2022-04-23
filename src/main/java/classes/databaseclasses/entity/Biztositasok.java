package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Biztositasok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nev;
    Integer dij;

    @ManyToOne
    @JoinColumn(name = "biztositok_id")
    Biztositok biztositok;
    @ManyToOne
    @JoinColumn(name = "utasok_id")
    Utasok utasok;

    public String biztositoNev(){
        return this.biztositok.nev;
    }
}
