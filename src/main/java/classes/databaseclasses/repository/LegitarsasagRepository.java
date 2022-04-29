package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Legitarsasag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegitarsasagRepository extends CrudRepository<Legitarsasag, String> {
}
