package com.example.lab9.domain;

import com.example.lab9.utils.MakeableJSON;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.example.lab9.utils.Utils.HTMLSpecialChars;

@Entity
@Table(name = "dept")
public final class Department extends DomainTable implements MakeableJSON, TableInterface {
    @Id
    @RegEx("^\\d{1,10}$")
    private int deptno;
    @RegEx("^\\w{1,50}$")
    private String dname;
    @RegEx("^\\w{0,50}$")
    private String loc;

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = HTMLSpecialChars(dname);
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = HTMLSpecialChars(loc);
    }

    public void setDeptno(String deptno) throws setFieldException {
        this.deptno = Integer.parseInt(deptno);
        if(deptRepo.existsRecordsByDeptno(this.deptno)){
            throw new setFieldException("PK Deptno already exist!");}
    }

    @Override
    public String toJSONStringObject() {
        return "{\"deptno\":" + deptno + ",\"dname\":\"" + ObjToStr(dname) + "\",\"loc\":\"" + ObjToStr(loc) + "\"}";
    }
}
