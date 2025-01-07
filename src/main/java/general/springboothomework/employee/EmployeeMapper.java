package general.springboothomework.employee;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeMapper {

    @Mapping(target = "id", source = "employee_id")
    @Mapping(target = "name", source = "employee_name")
    Employee toEmployee(EmployeeDTO employeeDTO);

    @InheritInverseConfiguration
    EmployeeDTO toEmployeeDTO(Employee employee);

}
