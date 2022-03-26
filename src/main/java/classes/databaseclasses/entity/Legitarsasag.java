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
public class Legitarsasag {

    @Id
    String nev;

    @OneToMany(mappedBy = "legitarsasag")
    List<Repulojaratok> repulojaratok;
}
