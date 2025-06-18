package org.example.empleadosapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TitlePK implements Serializable {

    private Integer empNo;
    private String title;
    private LocalDate fromDate;

    public TitlePK() {}

    public TitlePK(Integer empNo, String title, LocalDate fromDate) {
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TitlePK)) return false;
        TitlePK titlePK = (TitlePK) o;
        return Objects.equals(empNo, titlePK.empNo) &&
                Objects.equals(title, titlePK.title) &&
                Objects.equals(fromDate, titlePK.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, title, fromDate);
    }
}
