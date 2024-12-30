package general.springboothomework.repository;

import general.springboothomework.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u where upper(u.email) = upper(?1)")
    Optional<UserEntity> findByUniqueKey(String email);


}