package org.example.empleadosapp.service;

import org.example.empleadosapp.model.Empleado;
import org.example.empleadosapp.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    public Page<Empleado> obtenerTodosPaginados(Pageable pageable) {
        return empleadoRepository.findAll(pageable);
    }

    public Optional<Empleado> obtenerPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    public List<Empleado> filtrarPorIdConStream(Integer empNo) {
        return empleadoRepository.findAll().stream()
                .filter(e -> e.getEmpNo().equals(empNo))
                .toList();
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void eliminarPorId(Integer empNo) {
        empleadoRepository.deleteById(empNo);
    }

    public boolean existePorId(Integer empNo) {
        return empleadoRepository.existsById(empNo);
    }

    public Page<Empleado> getAllEmpleados(int page) {
        return empleadoRepository.findAll(PageRequest.of(page, 50));
    }

    public Empleado getEmpleadoById(Integer empNo) {
        return empleadoRepository.findByEmpNo(empNo);
    }
}
