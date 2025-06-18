package org.example.empleadosapp.repository;

import org.example.empleadosapp.model.Salary;
import org.example.empleadosapp.model.SalaryPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, SalaryPK> {
    List<Salary> findByEmpNo(Integer empNo);
    Page<Salary> findByEmpNo(Integer empNo, Pageable pageable);
}
