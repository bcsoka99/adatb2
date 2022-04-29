package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

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
        System.err.println("foglalasba bement");
        Integer foglalasSzam = 0;
        if (jegy == null || jegy.isEmpty()){
            System.err.println("jegy null vagy ures");
            return 0;
        }
        for (Jegy j : this.jegy){
            if (j.foglalt){
                foglalasSzam++;
                System.err.println("+ jegy");
            }
        }
        return foglalasSzam;
    }


}
