package general.springboothomework.repository;

import general.springboothomework.entity.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface GroupRepository extends CrudRepository<Group, Long> {
    Group findByName(String name);
}
