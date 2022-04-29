package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Utasok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtasokRepository extends CrudRepository<Utasok, Integer> {

    Utasok findByNev(@Param("nev") String nev);
}
