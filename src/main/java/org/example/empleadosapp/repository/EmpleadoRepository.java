package org.example.empleadosapp.repository;

import org.example.empleadosapp.model.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Page<Empleado> findAll(Pageable pageable);
    Empleado findByEmpNo(Integer empNo);
}