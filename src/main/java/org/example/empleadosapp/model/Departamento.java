package org.example.empleadosapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Departamento {

    @Id
    @Column(name = "dept_no", columnDefinition = "CHAR")
    private String deptNo;

    @Column(name = "dept_name")
    private String deptName;

    // Getters y Setters

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
