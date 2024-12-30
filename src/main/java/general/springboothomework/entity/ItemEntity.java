package general.springboothomework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "item_entity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private String itemDescription;
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

}