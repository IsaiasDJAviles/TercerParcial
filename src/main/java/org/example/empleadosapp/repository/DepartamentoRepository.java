package org.example.empleadosapp.repository;

import org.example.empleadosapp.model.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, String> {
    Page<Departamento> findAll(Pageable pageable);
    Departamento findByDeptNo(String deptNo);
}
