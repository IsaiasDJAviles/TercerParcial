package org.example.empleadosapp.controller;

import org.example.empleadosapp.model.Departamento;
import org.example.empleadosapp.model.EmpleadoDepartamento;
import org.example.empleadosapp.service.DepartamentoService;
import org.example.empleadosapp.service.EmpleadoDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private EmpleadoDepartamentoService empleadoDepartamentoService;

    @GetMapping
    public String listarDepartamentos(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Departamento> departamentos = departamentoService.obtenerTodosPaginados(PageRequest.of(page, 50));
        model.addAttribute("departamentos", departamentos);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", departamentos.getTotalPages());
        return "departamentos/lista";
    }

    @GetMapping("/{id}")
    public String verDepartamento(@PathVariable String id,
                                  @RequestParam(defaultValue = "0") int page,
                                  Model model) {
        Departamento departamento = departamentoService.obtenerPorId(id).orElse(null);
        if (departamento != null) {
            model.addAttribute("departamento", departamento);
            // Obtener empleados del departamento con paginación
            Page<EmpleadoDepartamento> deptEmps = empleadoDepartamentoService
                    .obtenerPorDepartamentoPaginado(id, PageRequest.of(page, 50));

            model.addAttribute("empleados", deptEmps.getContent().stream()
                    .map(EmpleadoDepartamento::getEmpleado)
                    .collect(Collectors.toList()));
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", deptEmps.getTotalPages());
            return "departamentos/detalle";
        }
        return "redirect:/departamentos";
    }

    // Obtener lista paginada
    @GetMapping("/api/departamentos")
    public ResponseEntity<Page<Departamento>> listarPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamaño) {
        Page<Departamento> departamentos = departamentoService.obtenerPaginados(pagina, tamaño);
        return ResponseEntity.ok(departamentos);
    }

    // Obtener todos (sin paginación)
    @GetMapping("/api/departamentos/todos")
    public ResponseEntity<List<Departamento>> listarTodos() {
        List<Departamento> departamentos = departamentoService.obtenerTodos();
        return ResponseEntity.ok(departamentos);
    }

    // Obtener por id (deptNo)
    @GetMapping("/api/departamentos/{deptNo}")
    public ResponseEntity<Departamento> obtenerPorId(@PathVariable String deptNo) {
        Optional<Departamento> optDep = departamentoService.obtenerPorId(deptNo);
        if (optDep.isPresent()) {
            return ResponseEntity.ok(optDep.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo departamento
    @PostMapping("/api/departamentos")
    public ResponseEntity<Departamento> crear(@RequestBody Departamento departamento) {
        if (departamentoService.existePorId(departamento.getDeptNo())) {
            return ResponseEntity.badRequest().build(); // Ya existe
        }
        Departamento guardado = departamentoService.guardar(departamento);
        return ResponseEntity.ok(guardado);
    }

    // Actualizar departamento existente
    @PutMapping("/api/departamentos/{deptNo}")
    public ResponseEntity<Departamento> actualizar(@PathVariable String deptNo,
                                                   @RequestBody Departamento departamento) {
        if (!departamentoService.existePorId(deptNo)) {
            return ResponseEntity.notFound().build();
        }
        // Para evitar cambiar el deptNo por error
        departamento.setDeptNo(deptNo);
        Departamento actualizado = departamentoService.guardar(departamento);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar departamento
    @DeleteMapping("/api/departamentos/{deptNo}")
    public ResponseEntity<Void> eliminar(@PathVariable String deptNo) {
        if (!departamentoService.existePorId(deptNo)) {
            return ResponseEntity.notFound().build();
        }
        departamentoService.eliminarPorId(deptNo);
        return ResponseEntity.noContent().build();
    }

    // Filtrar por nombre (opcional)
    @GetMapping("/api/departamentos/buscar")
    public ResponseEntity<List<Departamento>> buscarPorNombre(@RequestParam String nombre) {
        List<Departamento> encontrados = departamentoService.filtrarPorNombre(nombre);
        return ResponseEntity.ok(encontrados);
    }
}