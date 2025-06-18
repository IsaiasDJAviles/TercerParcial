package org.example.empleadosapp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "dept_emp")
@IdClass(EmpleadoDepartamentoPK.class)
public class EmpleadoDepartamento implements Serializable {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Id
    @Column(name = "dept_no", columnDefinition = "CHAR(4)")
    private String deptNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    private Departamento departamento;

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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
