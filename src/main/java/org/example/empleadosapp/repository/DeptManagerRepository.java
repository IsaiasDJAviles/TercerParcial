package org.example.empleadosapp.repository;

import org.example.empleadosapp.model.DeptManager;
import org.example.empleadosapp.model.DeptManagerPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptManagerPK> {
    List<DeptManager> findByDeptNo(String deptNo);
    Page<DeptManager> findByDeptNo(String deptNo, Pageable pageable);
}
