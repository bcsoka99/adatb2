package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Utasok;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtasokRepository extends CrudRepository<Utasok, Integer> {

    Utasok findByNev(@Param("nev") String nev);

    @Query(value = "select distinct n.nev from Utasok n")
    List<String> findAllNev();
}
