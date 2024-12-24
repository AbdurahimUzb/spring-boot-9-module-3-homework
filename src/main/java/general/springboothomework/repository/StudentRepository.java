package general.springboothomework.repository;

import general.springboothomework.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE YEAR(s.birthday) BETWEEN :startYear AND :endYear")
    List<Student> findByBirthYearRange(@Param("startYear") int startYear, @Param("endYear") int endYear);

    @Query(nativeQuery = true)
    List<Student> findByBirthYearRangeNative(@Param("startYear") int startYear, @Param("endYear") int endYear);

    List<Student> findByGender(@Param("gender") String gender);

    Page<Student> findAll(Pageable pageable);

    List<Student> findByGroupId(@Param("groupId") Long groupId);
}