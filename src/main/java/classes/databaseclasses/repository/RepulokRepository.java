package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Repulok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepulokRepository extends CrudRepository<Repulok, Integer> {
}
