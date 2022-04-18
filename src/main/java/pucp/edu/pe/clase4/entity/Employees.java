package pucp.edu.pe.clase4.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;


@Entity
@Table(name="employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeid;

    @NotBlank
    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name",nullable = false)
    @NotBlank
    private String lastname;

    @Column(nullable = false)
    @Email(message = "Se debe seguir el formato siguiente: nombre@correo.com")
    private String email;

    @NotBlank (message = "No puede dejar el campo vacío")
    private String password;

    @Column(name = "phone_number")
    private String phonenumber;

    @Column(name = "hire_date")
    private Date hiredate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs jobid;

    @Positive
    private int salary;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employees managerid;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments departmentid;
    private int enabled;

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Jobs getJobid() {
        return jobid;
    }

    public void setJobid(Jobs jobid) {
        this.jobid = jobid;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employees getManagerid() {
        return managerid;
    }

    public void setManagerid(Employees managerid) {
        this.managerid = managerid;
    }

    public Departments getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Departments departmentid) {
        this.departmentid = departmentid;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    //COMPLETAR
}
