package org.example.empleadosapp.service;

import org.example.empleadosapp.model.EmpleadoDepartamento;
import org.example.empleadosapp.model.EmpleadoDepartamentoPK;
import org.example.empleadosapp.repository.EmpleadoDepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoDepartamentoService {

    @Autowired
    private EmpleadoDepartamentoRepository empleadoDepartamentoRepository;

    public List<EmpleadoDepartamento> obtenerTodos() {
        return empleadoDepartamentoRepository.findAll();
    }

    public Optional<EmpleadoDepartamento> obtenerPorId(EmpleadoDepartamentoPK id) {
        return empleadoDepartamentoRepository.findById(id);
    }

    public EmpleadoDepartamento guardar(EmpleadoDepartamento ed) {
        return empleadoDepartamentoRepository.save(ed);
    }

    public void eliminarPorId(EmpleadoDepartamentoPK id) {
        empleadoDepartamentoRepository.deleteById(id);
    }

    public boolean existePorId(EmpleadoDepartamentoPK id) {
        return empleadoDepartamentoRepository.existsById(id);
    }

    public List<EmpleadoDepartamento> obtenerPorEmpleado(Integer empNo) {
        return empleadoDepartamentoRepository.findByEmpNo(empNo);
    }

    public List<EmpleadoDepartamento> obtenerPorDepartamento(String deptNo) {
        return empleadoDepartamentoRepository.findByDeptNo(deptNo);
    }

    public Page<EmpleadoDepartamento> obtenerPorDepartamentoPaginado(String deptNo, Pageable pageable) {
        return empleadoDepartamentoRepository.findByDeptNo(deptNo, pageable);
    }

    // Método corregido con stream:
    public Optional<EmpleadoDepartamento> filtrarPorIdConStream(EmpleadoDepartamentoPK id) {
        return empleadoDepartamentoRepository.findAll()
                .stream()
                .filter(ed -> ed.getEmpNo().equals(id.getEmpNo())
                        && ed.getDeptNo().equals(id.getDeptNo()))
                .findFirst();
    }
}
