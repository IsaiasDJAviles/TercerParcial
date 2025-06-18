package org.example.empleadosapp.controller;

import org.example.empleadosapp.model.EmpleadoDepartamento;
import org.example.empleadosapp.model.EmpleadoDepartamentoPK;
import org.example.empleadosapp.service.EmpleadoDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleado-departamento")
public class EmpleadoDepartamentoController {

    @Autowired
    private EmpleadoDepartamentoService empleadoDepartamentoService;

    // Obtener todos
    @GetMapping("/todos")
    public ResponseEntity<List<EmpleadoDepartamento>> listarTodos() {
        return ResponseEntity.ok(empleadoDepartamentoService.obtenerTodos());
    }

    // Obtener por emp_no y dept_no
    @GetMapping("/{empNo}/{deptNo}")
    public ResponseEntity<EmpleadoDepartamento> obtenerPorId(
            @PathVariable Integer empNo,
            @PathVariable String deptNo) {

        EmpleadoDepartamentoPK id = new EmpleadoDepartamentoPK(empNo, deptNo);
        Optional<EmpleadoDepartamento> resultado = empleadoDepartamentoService.obtenerPorId(id);

        return resultado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nuevo
    @PostMapping
    public ResponseEntity<EmpleadoDepartamento> crear(@RequestBody EmpleadoDepartamento ed) {
        EmpleadoDepartamentoPK id = new EmpleadoDepartamentoPK(
                ed.getEmpleado().getEmpNo(),
                ed.getDepartamento().getDeptNo()
        );

        if (empleadoDepartamentoService.existePorId(id)) {
            return ResponseEntity.badRequest().build(); // Ya existe
        }

        EmpleadoDepartamento guardado = empleadoDepartamentoService.guardar(ed);
        return ResponseEntity.ok(guardado);
    }

    // Actualizar existente
    @PutMapping("/{empNo}/{deptNo}")
    public ResponseEntity<EmpleadoDepartamento> actualizar(
            @PathVariable Integer empNo,
            @PathVariable String deptNo,
            @RequestBody EmpleadoDepartamento ed) {

        EmpleadoDepartamentoPK id = new EmpleadoDepartamentoPK(empNo, deptNo);
        if (!empleadoDepartamentoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        ed.getEmpleado().setEmpNo(empNo);
        ed.getDepartamento().setDeptNo(deptNo);

        EmpleadoDepartamento actualizado = empleadoDepartamentoService.guardar(ed);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar por ID compuesto
    @DeleteMapping("/{empNo}/{deptNo}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer empNo,
            @PathVariable String deptNo) {

        EmpleadoDepartamentoPK id = new EmpleadoDepartamentoPK(empNo, deptNo);
        if (!empleadoDepartamentoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        empleadoDepartamentoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todas las asignaciones por empleado
    @GetMapping("/empleado/{empNo}")
    public ResponseEntity<List<EmpleadoDepartamento>> porEmpleado(@PathVariable Integer empNo) {
        return ResponseEntity.ok(empleadoDepartamentoService.obtenerPorEmpleado(empNo));
    }

    // Obtener todas las asignaciones por departamento
    @GetMapping("/departamento/{deptNo}")
    public ResponseEntity<List<EmpleadoDepartamento>> porDepartamento(@PathVariable String deptNo) {
        return ResponseEntity.ok(empleadoDepartamentoService.obtenerPorDepartamento(deptNo));
    }
}
