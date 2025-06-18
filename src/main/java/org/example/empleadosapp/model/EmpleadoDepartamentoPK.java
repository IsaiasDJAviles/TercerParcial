package org.example.empleadosapp.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;

public class EmpleadoDepartamentoPK implements Serializable {

    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "dept_no", columnDefinition = "CHAR(4)")
    private String deptNo;

    // Constructor vacío obligatorio
    public EmpleadoDepartamentoPK() {}

    public EmpleadoDepartamentoPK(Integer empNo, String deptNo) {
        this.empNo = empNo;
        this.deptNo = deptNo;
    }

    // Getters (¡muy importantes!)
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

    // equals y hashCode obligatorios para claves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpleadoDepartamentoPK)) return false;
        EmpleadoDepartamentoPK that = (EmpleadoDepartamentoPK) o;
        return Objects.equals(empNo, that.empNo) &&
                Objects.equals(deptNo, that.deptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, deptNo);
    }
}
