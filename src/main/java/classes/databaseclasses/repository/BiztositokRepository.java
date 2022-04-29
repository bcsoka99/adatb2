package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Biztositok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiztositokRepository extends CrudRepository<Biztositok, Integer> {
}
