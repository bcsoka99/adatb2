package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Repulojaratok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepulojaratokRepository extends CrudRepository<Repulojaratok, Integer> {

    List<Repulojaratok> findAllByHonnan(@Param("Honnan") String Honnan);
}
