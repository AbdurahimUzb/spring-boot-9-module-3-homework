package general.springboothomework.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDAO {
    private Long id;
    private String name;
    private List<Long> students; // Talabalar ID ro'yxati
}