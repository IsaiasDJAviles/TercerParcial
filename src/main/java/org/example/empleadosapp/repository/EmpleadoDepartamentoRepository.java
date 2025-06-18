package org.example.empleadosapp.repository;

import org.example.empleadosapp.model.EmpleadoDepartamento;
import org.example.empleadosapp.model.EmpleadoDepartamentoPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoDepartamentoRepository extends JpaRepository<EmpleadoDepartamento, EmpleadoDepartamentoPK> {
    List<EmpleadoDepartamento> findByEmpNo(Integer empNo);
    List<EmpleadoDepartamento> findByDeptNo(String deptNo);
    Page<EmpleadoDepartamento> findByDeptNo(String deptNo, Pageable pageable);
}
