package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Szallodak;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SzallodakRepository extends CrudRepository<Szallodak, Integer> {

    List<Szallodak> findAllByTelepules(@Param("Telepules") String telepules);

    @Query(value = "select distinct v.telepules from Szallodak v")
    List<String> findAllTelepules();
}
