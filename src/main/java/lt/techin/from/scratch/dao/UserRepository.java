package lt.techin.from.scratch.dao;

import lt.techin.from.scratch.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Person, Long> {

}
