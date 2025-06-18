package org.example.empleadosapp.service;

import org.example.empleadosapp.model.DeptManager;
import org.example.empleadosapp.model.DeptManagerPK;
import org.example.empleadosapp.repository.DeptManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeptManagerService {

    @Autowired
    private DeptManagerRepository deptManagerRepository;

    public List<DeptManager> obtenerTodos() {
        return deptManagerRepository.findAll();
    }

    public Page<DeptManager> obtenerPaginados(Pageable pageable) {
        return deptManagerRepository.findAll(pageable);
    }

    public Optional<DeptManager> obtenerPorId(DeptManagerPK id) {
        return deptManagerRepository.findById(id);
    }

    public Optional<DeptManager> filtrarPorIdConStream(DeptManagerPK id) {
        return deptManagerRepository.findAll()
                .stream()
                .filter(dm -> dm.getEmpNo().equals(id.getEmpNo())
                        && dm.getDeptNo().equals(id.getDeptNo()))
                .findFirst();
    }

    public DeptManager guardar(DeptManager deptManager) {
        return deptManagerRepository.save(deptManager);
    }

    public void eliminarPorId(DeptManagerPK id) {
        deptManagerRepository.deleteById(id);
    }

    public boolean existePorId(DeptManagerPK id) {
        return deptManagerRepository.existsById(id);
    }

    public List<DeptManager> obtenerPorDepartamento(String deptNo) {
        return deptManagerRepository.findByDeptNo(deptNo);
    }

    public Page<DeptManager> obtenerPorDepartamentoPaginado(String deptNo, Pageable pageable) {
        return deptManagerRepository.findByDeptNo(deptNo, pageable);
    }
}
