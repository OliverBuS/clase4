package pucp.edu.pe.clase4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pucp.edu.pe.clase4.entity.Employees;
import pucp.edu.pe.clase4.repository.DepartmentsRepository;
import pucp.edu.pe.clase4.repository.EmployeesRepository;
import pucp.edu.pe.clase4.repository.LocationsRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/Search")
public class SearchController {
    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Autowired
    LocationsRepository locationsRepository;


    @GetMapping(value = {"","/"})
    public String indice(){
        return "Search/indice";
    }

    @GetMapping(value = {"/Salario"})
    public String listaEmpleadosMayorSalrio (Model model){



        return "Search/lista2";
    }

    @PostMapping("/busqueda")
    public String buscar (@RequestParam("search") @Valid Integer busqueda){


        return "Search/lista2";

        //COMPLETAR
    }

    @GetMapping(value = "/Filtro2")
    public String cantidadEmpleadosPorPais (Model model){

        //COMPLETAR
        model.addAttribute("listaDepSalario", departmentsRepository.listaDepSalario());

        return "/Search/salario";
    }

    @GetMapping("/listar")
    public String listarEmpleadoDep(@ModelAttribute("employees") Employees employees,
                                    @RequestParam("id") int depid, Model model, RedirectAttributes attr) {

        model.addAttribute("listaEmpleadoxDep", departmentsRepository.listaEmpleadoxDep(depid));
        //COMPLETAR
        return "/Search/lista3";

    }


}
