package com.example.lab9.domain;

import com.example.lab9.repos.DeptRepo;
import com.example.lab9.repos.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomainTable {
    static DeptRepo deptRepo;
    static EmpRepo empRepo;

    @Autowired
    public void setRepo(DeptRepo deptRepo, EmpRepo empRepo){
        System.out.println("GGRRR");
        DomainTable.deptRepo = deptRepo;
        DomainTable.empRepo = empRepo;
    }


    String ObjToStr(Object obj){
        return obj == null?"":obj.toString();
    }

    public static final class setFieldException extends Exception{
        public setFieldException(String message) {
            super(message);
        }
        public setFieldException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
