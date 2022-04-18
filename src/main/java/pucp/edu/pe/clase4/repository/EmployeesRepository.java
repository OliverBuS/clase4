package pucp.edu.pe.clase4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pucp.edu.pe.clase4.entity.Employees;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    @Query(value="select * from employees e " +
            "where e.first_name like %?1% or e.last_name like %?1%\n",nativeQuery = true)
    List<Employees> listarEmpleadosPorNombreApellido(String name);

}
