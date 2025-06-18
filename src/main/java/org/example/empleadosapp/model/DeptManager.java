package org.example.empleadosapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dept_manager")
@IdClass(DeptManagerPK.class)
public class DeptManager {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Id
    @Column(name = "dept_no", columnDefinition = "CHAR")
    private String deptNo;

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

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
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
