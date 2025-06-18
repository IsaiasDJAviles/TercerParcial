package org.example.empleadosapp.service;

import org.example.empleadosapp.model.Departamento;
import org.example.empleadosapp.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Page<Departamento> getAllDepartamentos(int page) {
        return departamentoRepository.findAll(PageRequest.of(page, 50));
    }

    public Departamento getDepartamentoById(String deptNo) {
        return departamentoRepository.findByDeptNo(deptNo);
    }

    // Obtener departamentos paginados
    public Page<Departamento> obtenerPaginados(int pagina, int tamaño) {
        return departamentoRepository.findAll(PageRequest.of(pagina, tamaño));
    }

    // Obtener todos como lista (para ejemplo con streams)
    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    // Filtrar departamentos por nombre con Streams (ejemplo)
    public List<Departamento> filtrarPorNombre(String nombreParcial) {
        List<Departamento> todos = departamentoRepository.findAll();
        return todos.stream()
                .filter(d -> d.getDeptName().toLowerCase().contains(nombreParcial.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<Departamento> obtenerPorId(String id) {
        return departamentoRepository.findById(id);
    }

    public Departamento guardar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public void eliminarPorId(String deptNo) {
        departamentoRepository.deleteById(deptNo);
    }

    public boolean existePorId(String deptNo) {
        return departamentoRepository.existsById(deptNo);
    }

    public Page<Departamento> obtenerTodosPaginados(Pageable pageable) {
        return departamentoRepository.findAll(pageable);
    }

}
