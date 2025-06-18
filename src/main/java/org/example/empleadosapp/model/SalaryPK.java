package org.example.empleadosapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SalaryPK implements Serializable {

    private Integer empNo;
    private LocalDate fromDate;

    public SalaryPK() {}

    public SalaryPK(Integer empNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
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
        if (!(o instanceof SalaryPK)) return false;
        SalaryPK salaryPK = (SalaryPK) o;
        return Objects.equals(empNo, salaryPK.empNo) &&
                Objects.equals(fromDate, salaryPK.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }
}
