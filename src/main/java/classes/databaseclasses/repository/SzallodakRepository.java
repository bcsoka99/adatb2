package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Szallodak;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SzallodakRepository extends CrudRepository<Szallodak, Integer> {

    List<Szallodak> findAllByTelepules(@Param("Telepules") String telepules);
}
