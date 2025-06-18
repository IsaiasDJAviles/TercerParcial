package org.example.empleadosapp.repository;

import org.example.empleadosapp.model.Title;
import org.example.empleadosapp.model.TitlePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, TitlePK> {
    List<Title> findByEmpNo(Integer empNo);
    Page<Title> findByEmpNo(Integer empNo, Pageable pageable);
}
