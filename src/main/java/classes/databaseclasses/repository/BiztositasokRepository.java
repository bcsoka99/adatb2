package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Biztositasok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiztositasokRepository extends CrudRepository<Biztositasok, Integer> {
}
