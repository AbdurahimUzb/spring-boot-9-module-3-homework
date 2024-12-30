package general.springboothomework.repository;

import general.springboothomework.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreEntityRepository extends JpaRepository<StoreEntity, Long> {
}