package com.example.lab9.domain;

import com.example.lab9.utils.MakeableJSON;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.*;
import java.util.Date;


import static com.example.lab9.utils.Utils.HTMLSpecialChars;


@Entity
@Table(name = "emp")
public final class Employee extends DomainTable implements MakeableJSON, TableInterface {
    @Id
    @RegEx("^\\d{1,10}$")
    private int empno;
    @RegEx("^\\w{1,50}$")
    private String ename;
    @RegEx("^\\w{1,50}$")
    private String job;
    @RegEx("^\\d{0,10}$")
    private Integer mgr;
    @RegEx("^\\d{4}(-\\d\\d){2}$")
    private Date hiredate;
    @RegEx("^\\d{1,10}(\\.?\\d{1,2})?$")
    private Double sal;
    @RegEx("^\\d{0,10}(\\.?\\d{1,2})?$")
    private Double comm;
    @RegEx("^\\d{0,10}$")
    private Integer deptno;

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setEname(String ename) {
        this.ename = HTMLSpecialChars(ename);
    }

    public void setJob(String job) {
        this.job = HTMLSpecialChars(job);
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public void setHiredate(String hiredate) {
        try { this.hiredate = dateFormat.parse(hiredate);
        } catch (ParseException ignored) {}
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }


    public void setEmpno(String empno) throws setFieldException {
        this.empno = Integer.parseInt(empno);
        if(empRepo.existsRecordsByEmpno(this.empno)) throw new setFieldException("PK Empno already exist!");
    }
    public void setSal(String sal) {
        this.sal = Double.parseDouble(sal);
    }
    public void setComm(String comm) {
        this.comm = Double.parseDouble(comm);
    }
    public void setDeptno(String deptno) throws setFieldException {
        this.deptno = Integer.parseInt(deptno);
        if(!deptRepo.existsRecordsByDeptno(this.deptno)) throw new setFieldException("FK Deptno in table dept not exist!");
    }

    @Override
    public String toJSONStringObject() {
        return "{\"empno\":" + empno +
                ",\"ename\":\"" + ObjToStr(ename) +
                "\",\"job\":\"" + ObjToStr(job) +
                "\",\"mgr\":" + mgr +
                ",\"hiredate\":\"" + dateFormat.format(hiredate) +
                "\",\"sal\":" + sal +
                ",\"comm\":" + comm +
                ",\"deptno\":" + deptno + '}';
    }
}
