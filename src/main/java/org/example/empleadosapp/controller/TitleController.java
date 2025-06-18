package org.example.empleadosapp.controller;

import org.example.empleadosapp.model.Title;
import org.example.empleadosapp.model.TitlePK;
import org.example.empleadosapp.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    // Obtener todos
    @GetMapping("/todos")
    public ResponseEntity<List<Title>> listarTodos() {
        return ResponseEntity.ok(titleService.obtenerTodos());
    }

    // Obtener paginados
    @GetMapping
    public ResponseEntity<Page<Title>> listarPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer empNo) {

        if (empNo != null) {
            return ResponseEntity.ok(titleService.obtenerPorEmpleadoPaginado(empNo, PageRequest.of(page, size)));
        }

        return ResponseEntity.ok(titleService.obtenerPorEmpleadoPaginado(0, PageRequest.of(page, size)));
    }

    // Obtener por ID compuesto
    @GetMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<Title> obtenerPorId(
            @PathVariable Integer empNo,
            @PathVariable String title,
            @PathVariable String fromDate) {

        LocalDate fecha = LocalDate.parse(fromDate);
        TitlePK id = new TitlePK(empNo, title, fecha);

        Optional<Title> resultado = titleService.obtenerPorId(id);
        return resultado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nuevo
    @PostMapping
    public ResponseEntity<Title> crear(@RequestBody Title title) {
        TitlePK id = new TitlePK(title.getEmpNo(), title.getTitle(), title.getFromDate());

        if (titleService.existePorId(id)) {
            return ResponseEntity.badRequest().build();
        }

        Title guardado = titleService.guardar(title);
        return ResponseEntity.ok(guardado);
    }

    // Actualizar existente
    @PutMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<Title> actualizar(
            @PathVariable Integer empNo,
            @PathVariable String title,
            @PathVariable String fromDate,
            @RequestBody Title nuevoTitle) {

        LocalDate fecha = LocalDate.parse(fromDate);
        TitlePK id = new TitlePK(empNo, title, fecha);

        if (!titleService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        nuevoTitle.setEmpNo(empNo);
        nuevoTitle.setTitle(title);
        nuevoTitle.setFromDate(fecha);

        Title actualizado = titleService.guardar(nuevoTitle);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar por ID
    @DeleteMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer empNo,
            @PathVariable String title,
            @PathVariable String fromDate) {

        LocalDate fecha = LocalDate.parse(fromDate);
        TitlePK id = new TitlePK(empNo, title, fecha);

        if (!titleService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        titleService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener títulos por número de empleado
    @GetMapping("/empleado/{empNo}")
    public ResponseEntity<List<Title>> obtenerPorEmpleado(@PathVariable Integer empNo) {
        return ResponseEntity.ok(titleService.obtenerPorEmpleado(empNo));
    }
}
