package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Szobafoglalas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SzobafoglalasRepository extends CrudRepository<Szobafoglalas, Integer> {
}
