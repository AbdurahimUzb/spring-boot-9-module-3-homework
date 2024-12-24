package general.springboothomework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "STUDENT")
@NamedQuery(
        name = "Student.findByGender",
        query = "SELECT s FROM Student s WHERE s.gender = :gender"
)
@NamedNativeQuery(
        name = "Student.findByBirthYearRangeNative",
        query = "SELECT * FROM Student WHERE YEAR(birthday) BETWEEN :startYear AND :endYear",
        resultClass = Student.class
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STUDENT_NAME", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @Column(nullable = false)
    private Integer age;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}