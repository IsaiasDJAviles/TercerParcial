package org.example.empleadosapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import org.example.empleadosapp.model.Salary;

@Entity
@Table(name = "salaries")
@IdClass(SalaryPK.class)
public class Salary {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "salary")
    private Integer salary;

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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
