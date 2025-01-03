package general.springboothomework.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import java.io.Serializable;

/**
 * DTO for {@link general.springboothomework.entity.StoreEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class StoreEntityDto implements Serializable {

    @NotNull
    @Min(1)
    @Parameter(description = "Store Identifier", required = true)
    private Long id;

    @Size(min = 3, max = 120)
    @NotBlank
    @Parameter(description = "Store Name", required = true)
    private String name;

    @Size(min = 9, max = 250)
    @NotBlank
    @Parameter(description = "Store Email", required = true)
    private String email;

    @NotNull
    @Min(1)
    @Parameter(description = "Store EmployeeCount", required = true)
    private int employeeCount;

    @Parameter(description = "Store Description")
    private String desc;
}