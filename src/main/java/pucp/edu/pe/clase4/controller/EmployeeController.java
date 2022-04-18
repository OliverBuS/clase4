package pucp.edu.pe.clase4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pucp.edu.pe.clase4.entity.Departments;
import pucp.edu.pe.clase4.entity.Employees;
import pucp.edu.pe.clase4.repository.DepartmentsRepository;
import pucp.edu.pe.clase4.repository.EmployeesRepository;
import pucp.edu.pe.clase4.repository.JobsRepository;


import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping(value = {""})
    public String listaEmployee(Model model){
        model.addAttribute("listaEmployee", employeesRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("/new")
    public String nuevoEmployeeForm(@ModelAttribute("employees") Employees employees, Model model) {
        model.addAttribute("listaDepartaments", departmentsRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());

        List<Departments> departmentOpt = departmentsRepository.findAll();
        List<Departments> departamentosFinales = new ArrayList<Departments>();
        for (Departments i : departmentOpt){
            if(i.getManagerid() != null){
                departamentosFinales.add(i);
            }
        }
        model.addAttribute("listaDepartamentosconJefes", departamentosFinales);

        return "employee/formulario";
    }

    @PostMapping("/save")
    public String guardarEmployee(@ModelAttribute("employees") @Valid Employees employees, BindingResult bindingResult,
                                  RedirectAttributes attr,
                                  @RequestParam(name="fechaContrato", required=false) String fechaContrato, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/formulario";
        }else {

            if (employees.getEmployeeid() == 0) {
                attr.addFlashAttribute("msg", "Empleado creado exitosamente");
                employees.setHiredate(new Date());
                employeesRepository.save(employees);
                return "redirect:/employee";
            } else {

                try {
                    employees.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(fechaContrato));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                employeesRepository.save(employees);
                attr.addFlashAttribute("msg", "Empleado actualizado exitosamente");
                return "redirect:/employee";
            }
        }
    }

    @GetMapping("/edit")
    public String editarEmployee(Model model, @RequestParam("id") int id, @ModelAttribute("employees") Employees employees, RedirectAttributes redirectAttributes) {
        Optional<Employees> employeesOptional = employeesRepository.findById(id);
        if (employeesOptional.isPresent()) {
            employees = employeesOptional.get();

            model.addAttribute("employees", employees);
            model.addAttribute("listaDepartaments", departmentsRepository.findAll());
            List<Departments> departmentOpt = departmentsRepository.findAll();
            List<Departments> departamentosFinales = new ArrayList<Departments>();
            for (Departments i : departmentOpt){
                if(i.getManagerid() != null){
                    departamentosFinales.add(i);
                }
            }
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaDepartamentosconJefes", departamentosFinales);
            return "employee/formulario";
        } else {
            return "redirect:/employee";
        }

    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Employees> optEmployees = employeesRepository.findById(id);

        if (optEmployees.isPresent()) {
            employeesRepository.deleteById(id);
            attr.addFlashAttribute("msg","Empleado borrado exitosamente");
        }
        return "redirect:/employee";

    }

    @PostMapping("/search")
    public String buscar (@RequestParam("name") String name, Model model){
        List<Employees> employeesOpt = employeesRepository.listarEmpleadosPorNombreApellido(name);
        model.addAttribute("listaEmployee",employeesOpt);
        return "employee/lista";
    }

}
