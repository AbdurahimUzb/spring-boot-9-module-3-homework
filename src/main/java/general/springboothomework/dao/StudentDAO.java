package general.springboothomework.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDAO {
    private Long id;
    private String name;
    private Integer age;
    private String birthday; // `yyyy-MM-dd` formatida
    private String gender;  // (`MEN` yoki `WOMEN`)
    private Long groupId;   // Talabaning guruhi
}