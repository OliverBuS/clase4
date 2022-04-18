package pucp.edu.pe.clase4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pucp.edu.pe.clase4.dto.EmpMayorSalarioDto;
import pucp.edu.pe.clase4.entity.Employees;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    @Query(value="select * from employees e " +
            "where e.first_name like %?1% or e.last_name like %?1%\n",nativeQuery = true)
    List<Employees> listarEmpleadosPorNombreApellido(String name);

    @Query(value = "select e.first_name as nombre, e.last_name as apellido, jh.start_date as fechainicio, jh.end_date as fechafin, j.job_title as puesto\n" +
            "from job_history jh\n" +
            "join employees e on (jh.employee_id= e.employee_id)\n" +
            "join jobs j on (e.job_id=j.job_id)\n" +
            "where e.salary > 8000;" ,nativeQuery = true)
    List<EmpMayorSalarioDto> empleadoMayor();

    @Query(value = "select e.first_name as nombre, e.last_name as apellido, jh.start_date as fechainicio, jh.end_date as fechafin, j.job_title as puesto\n" +
            "from job_history jh\n" +
            "join employees e on (jh.employee_id= e.employee_id)\n" +
            "join jobs j on (e.job_id=j.job_id)\n" +
            "where e.salary > ?1",nativeQuery = true)
    List<EmpMayorSalarioDto> listaMayorSalario(int salary);
}
