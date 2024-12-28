package general.springboothomework.repository;

import general.springboothomework.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    @Query("select a from AuthUser a where a.id = ?1")
    Optional<AuthUser> getAuthUserById(Long id);
}