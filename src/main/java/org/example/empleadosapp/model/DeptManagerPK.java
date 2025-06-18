package org.example.empleadosapp.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;

public class DeptManagerPK implements Serializable {

    @Column(name = "emp_no", columnDefinition = "INT")
    private Integer empNo;

    @Column(name = "dept_no", columnDefinition = "CHAR")
    private String deptNo;

    public DeptManagerPK() {}

    public DeptManagerPK(Integer empNo, String deptNo) {
        this.empNo = empNo;
        this.deptNo = deptNo;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeptManagerPK)) return false;
        DeptManagerPK that = (DeptManagerPK) o;
        return Objects.equals(empNo, that.empNo) &&
                Objects.equals(deptNo, that.deptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, deptNo);
    }
}
