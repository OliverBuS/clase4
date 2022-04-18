package pucp.edu.pe.clase4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pucp.edu.pe.clase4.repository.DepartmentsRepository;
import pucp.edu.pe.clase4.repository.EmployeesRepository;
import pucp.edu.pe.clase4.repository.HistoryRepository;
import org.springframework.ui.Model;


@Controller
@RequestMapping(value = "/history")
public class HistoryController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping(value = {"","/"})
    public String historialEmpleado(Model model){
        model.addAttribute("listaHistorial", historyRepository.listaHistorial());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        model.addAttribute("listaEmployees", employeesRepository.findAll());
        return "history/lista";
    }



}
