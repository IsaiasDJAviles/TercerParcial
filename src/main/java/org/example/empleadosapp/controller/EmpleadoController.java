package org.example.empleadosapp.controller;

import org.example.empleadosapp.model.Empleado;
import org.example.empleadosapp.model.Departamento;
import org.example.empleadosapp.model.EmpleadoDepartamento;
import org.example.empleadosapp.service.EmpleadoService;
import org.example.empleadosapp.service.DepartamentoService;
import org.example.empleadosapp.service.EmpleadoDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private EmpleadoDepartamentoService empleadoDepartamentoService;

    @GetMapping("/empleados")
    public String listarEmpleados(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Empleado> empleados = empleadoService.obtenerTodosPaginados(PageRequest.of(page, 50));
        model.addAttribute("empleados", empleados);
        model.addAttribute("currentPage", page);
        return "empleados/lista";
    }

    @GetMapping("/empleados/{id}")
    public String verEmpleado(@PathVariable Integer id, Model model) {
        Empleado empleado = empleadoService.obtenerPorId(id).orElse(null);
        if (empleado != null) {
            model.addAttribute("empleado", empleado);
            // Obtener departamentos del empleado
            List<EmpleadoDepartamento> deptEmps = empleadoDepartamentoService.obtenerPorEmpleado(id);
            model.addAttribute("departamentos", deptEmps.stream()
                    .map(EmpleadoDepartamento::getDepartamento)
                    .collect(Collectors.toList()));
            return "empleados/detalle";
        }
        return "redirect:/empleados";
    }

    @GetMapping("/buscar")
    public String buscarEmpleado(@RequestParam(required = false) Integer empNo, Model model) {
        if (empNo != null) {
            Empleado empleado = empleadoService.obtenerPorId(empNo).orElse(null);
            if (empleado != null) {
                model.addAttribute("empleado", empleado);
                // Obtener departamentos del empleado
                List<EmpleadoDepartamento> deptEmps = empleadoDepartamentoService.obtenerPorEmpleado(empNo);
                model.addAttribute("departamentos", deptEmps.stream()
                        .map(EmpleadoDepartamento::getDepartamento)
                        .collect(Collectors.toList()));
            }
        }
        return "empleados/buscar";
    }
}
