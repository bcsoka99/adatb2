package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Jegy;
import classes.databaseclasses.entity.Repulojaratok;
import classes.databaseclasses.entity.Utasok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JegyRepository extends CrudRepository<Jegy, Integer> {

    List<Jegy> findAllByUtasok(@Param("utasok") Utasok utasok);

    List<Jegy> findAllByRepulojaratokAndFoglalt(@Param("Repulojaratok") Repulojaratok Repulojaratok, @Param("foglalt") Boolean foglalt);
}
