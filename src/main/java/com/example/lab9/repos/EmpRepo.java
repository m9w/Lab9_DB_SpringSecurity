package com.example.lab9.repos;

import com.example.lab9.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Employee,Integer> {
    boolean existsRecordsByEmpno(int empno);
}
