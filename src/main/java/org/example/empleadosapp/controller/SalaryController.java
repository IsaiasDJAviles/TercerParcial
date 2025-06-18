package org.example.empleadosapp.controller;

import org.example.empleadosapp.model.Salary;
import org.example.empleadosapp.model.SalaryPK;
import org.example.empleadosapp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    // Obtener todos
    @GetMapping("/todos")
    public ResponseEntity<List<Salary>> listarTodos() {
        return ResponseEntity.ok(salaryService.obtenerTodos());
    }

    // Obtener paginados
    @GetMapping
    public ResponseEntity<Page<Salary>> listarPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(salaryService.obtenerPaginados(PageRequest.of(page, size)));
    }

    // Obtener por clave compuesta
    @GetMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Salary> obtenerPorId(
            @PathVariable Integer empNo,
            @PathVariable String fromDate) {
        LocalDate fecha = LocalDate.parse(fromDate);
        SalaryPK id = new SalaryPK(empNo, fecha);
        Optional<Salary> resultado = salaryService.obtenerPorId(id);

        return resultado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nuevo registro de salario
    @PostMapping
    public ResponseEntity<Salary> crear(@RequestBody Salary salary) {
        SalaryPK id = new SalaryPK(salary.getEmpNo(), salary.getFromDate());
        if (salaryService.existePorId(id)) {
            return ResponseEntity.badRequest().build(); // Ya existe
        }

        Salary guardado = salaryService.guardar(salary);
        return ResponseEntity.ok(guardado);
    }

    // Actualizar un salario existente
    @PutMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Salary> actualizar(
            @PathVariable Integer empNo,
            @PathVariable String fromDate,
            @RequestBody Salary salary) {

        LocalDate fecha = LocalDate.parse(fromDate);
        SalaryPK id = new SalaryPK(empNo, fecha);
        if (!salaryService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        salary.setEmpNo(empNo);
        salary.setFromDate(fecha);

        Salary actualizado = salaryService.guardar(salary);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar salario por ID compuesto
    @DeleteMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer empNo,
            @PathVariable String fromDate) {

        LocalDate fecha = LocalDate.parse(fromDate);
        SalaryPK id = new SalaryPK(empNo, fecha);
        if (!salaryService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        salaryService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los salarios de un empleado
    @GetMapping("/empleado/{empNo}")
    public ResponseEntity<List<Salary>> obtenerPorEmpleado(@PathVariable Integer empNo) {
        return ResponseEntity.ok(salaryService.obtenerPorEmpleado(empNo));
    }
}
