package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Jegy;
import org.springframework.data.repository.CrudRepository;

public interface JegyRepository extends CrudRepository<Jegy, Integer> {
}
