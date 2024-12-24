package general.springboothomework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "GROUP")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "GROUP_NAME", nullable = false)
    private String name;
    @OneToMany(mappedBy = "STUDENT", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;
}
