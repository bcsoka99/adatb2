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
public class Repulojaratok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Date indulas;
    Date erkezes;
    String honnan;
    String hova;
    Boolean etkezes;

    @ManyToOne
    @JoinColumn(name = "legitarsasag_id")
    Legitarsasag legitarsasag;
    @OneToMany(mappedBy = "repulojaratok")
    List<Repulok> repulok;
    @OneToMany(mappedBy = "repulojaratok")
    List<Jegy> jegy;

    public String legitarsasagNev(){
        return this.getLegitarsasag().nev;
    }

    public Integer foglalasSzam(){
        Integer foglalasSzam = 0;
        if (jegy == null || jegy.isEmpty()){
            return 0;
        }
        for (Jegy j : this.jegy){
            if (j.foglalt){
                foglalasSzam++;
            }
        }
        return foglalasSzam;
    }

    @Override
    public String toString() {
        return
                "indulas=" + honnan +" "+ indulas +
                ", erkezes=" +hova+ " "+ erkezes
                ;
    }
}
