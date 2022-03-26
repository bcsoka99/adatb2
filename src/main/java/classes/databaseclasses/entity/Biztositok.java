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
public class Biztositok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nev;
    @OneToMany(mappedBy = "biztositok")
    List<Biztositasok> biztositasok;
}
