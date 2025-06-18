package org.example.empleadosapp.service;

import org.example.empleadosapp.model.Salary;
import org.example.empleadosapp.model.SalaryPK;
import org.example.empleadosapp.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    public List<Salary> obtenerTodos() {
        return salaryRepository.findAll();
    }

    public Page<Salary> obtenerPaginados(Pageable pageable) {
        return salaryRepository.findAll(pageable);
    }

    public Optional<Salary> obtenerPorId(SalaryPK id) {
        return salaryRepository.findById(id);
    }

    public Optional<Salary> filtrarPorIdConStream(SalaryPK id) {
        List<Salary> lista = salaryRepository.findAll();
        for (Salary s : lista) {
            if (s.getEmpNo().equals(id.getEmpNo()) && s.getFromDate().equals(id.getFromDate())) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public Salary guardar(Salary salary) {
        return salaryRepository.save(salary);
    }

    public void eliminarPorId(SalaryPK id) {
        salaryRepository.deleteById(id);
    }

    public boolean existePorId(SalaryPK id) {
        return salaryRepository.existsById(id);
    }

    public List<Salary> obtenerPorEmpleado(Integer empNo) {
        return salaryRepository.findByEmpNo(empNo);
    }
}
