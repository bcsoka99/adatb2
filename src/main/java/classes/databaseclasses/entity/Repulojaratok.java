package classes.databaseclasses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

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

    @ManyToOne
    @JoinColumn(name = "legitarsasag_id")
    Legitarsasag legitarsasag;
    @OneToMany(mappedBy = "repulojaratok")
    List<Repulok> repulok;
    @OneToMany(mappedBy = "repulojaratok")
    List<Jegy> jegy;


}
