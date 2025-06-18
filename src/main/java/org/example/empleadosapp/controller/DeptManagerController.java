package org.example.empleadosapp.controller;

import org.example.empleadosapp.model.DeptManager;
import org.example.empleadosapp.model.DeptManagerPK;
import org.example.empleadosapp.service.DeptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deptmanagers")
public class DeptManagerController {

    @Autowired
    private DeptManagerService deptManagerService;

    // Obtener todos sin paginación
    @GetMapping("/todos")
    public ResponseEntity<List<DeptManager>> listarTodos() {
        List<DeptManager> lista = deptManagerService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener paginados
    @GetMapping
    public ResponseEntity<Page<DeptManager>> listarPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamaño) {
        Page<DeptManager> paginaDepts = deptManagerService.obtenerPaginados(PageRequest.of(pagina, tamaño));
        return ResponseEntity.ok(paginaDepts);
    }

    // Obtener por clave compuesta
    @GetMapping("/id")
    public ResponseEntity<DeptManager> obtenerPorId(
            @RequestParam Integer empNo,
            @RequestParam String deptNo) {

        DeptManagerPK id = new DeptManagerPK(empNo, deptNo);
        Optional<DeptManager> opt = deptManagerService.obtenerPorId(id);
        return opt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nuevo DeptManager
    @PostMapping
    public ResponseEntity<DeptManager> crear(@RequestBody DeptManager deptManager) {
        DeptManagerPK id = new DeptManagerPK(deptManager.getEmpNo(), deptManager.getDeptNo());
        if (deptManagerService.existePorId(id)) {
            return ResponseEntity.badRequest().build(); // Ya existe
        }
        DeptManager guardado = deptManagerService.guardar(deptManager);
        return ResponseEntity.ok(guardado);
    }

    // Actualizar existente
    @PutMapping("/id")
    public ResponseEntity<DeptManager> actualizar(
            @RequestParam Integer empNo,
            @RequestParam String deptNo,
            @RequestBody DeptManager deptManager) {

        DeptManagerPK id = new DeptManagerPK(empNo, deptNo);
        if (!deptManagerService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        // Asegurar que no cambien las claves
        deptManager.setEmpNo(empNo);
        deptManager.setDeptNo(deptNo);

        DeptManager actualizado = deptManagerService.guardar(deptManager);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar por clave compuesta
    @DeleteMapping("/id")
    public ResponseEntity<Void> eliminar(
            @RequestParam Integer empNo,
            @RequestParam String deptNo) {

        DeptManagerPK id = new DeptManagerPK(empNo, deptNo);
        if (!deptManagerService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        deptManagerService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener por deptNo (filtrar)
    @GetMapping("/departamento/{deptNo}")
    public ResponseEntity<List<DeptManager>> obtenerPorDepartamento(@PathVariable String deptNo) {
        List<DeptManager> lista = deptManagerService.obtenerPorDepartamento(deptNo);
        return ResponseEntity.ok(lista);
    }

    // Obtener paginado por deptNo
    @GetMapping("/departamento/{deptNo}/paginado")
    public ResponseEntity<Page<DeptManager>> obtenerPorDepartamentoPaginado(
            @PathVariable String deptNo,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamaño) {

        Page<DeptManager> paginaDepts = deptManagerService.obtenerPorDepartamentoPaginado(deptNo, PageRequest.of(pagina, tamaño));
        return ResponseEntity.ok(paginaDepts);
    }
}