package org.example.empleadosapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "titles")
@IdClass(TitlePK.class)
public class Title {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Id
    @Column(name = "title")
    private String title;

    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    // Getters y Setters

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
