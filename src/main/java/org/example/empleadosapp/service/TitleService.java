package org.example.empleadosapp.service;

import org.example.empleadosapp.model.Title;
import org.example.empleadosapp.model.TitlePK;
import org.example.empleadosapp.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;

    public List<Title> obtenerTodos() {
        return titleRepository.findAll();
    }

    public Optional<Title> obtenerPorId(TitlePK id) {
        return titleRepository.findById(id);
    }

    public Title guardar(Title title) {
        return titleRepository.save(title);
    }

    public void eliminarPorId(TitlePK id) {
        titleRepository.deleteById(id);
    }

    public boolean existePorId(TitlePK id) {
        return titleRepository.existsById(id);
    }

    public List<Title> obtenerPorEmpleado(Integer empNo) {
        return titleRepository.findByEmpNo(empNo);
    }

    public Page<Title> obtenerPorEmpleadoPaginado(Integer empNo, Pageable pageable) {
        return titleRepository.findByEmpNo(empNo, pageable);
    }

    public Optional<Title> filtrarPorIdConStream(TitlePK id) {
        return titleRepository.findAll().stream()
                .filter(t -> t.getEmpNo().equals(id.getEmpNo()) &&
                        t.getTitle().equals(id.getTitle()) &&
                        t.getFromDate().equals(id.getFromDate()))
                .findFirst();
    }
}
