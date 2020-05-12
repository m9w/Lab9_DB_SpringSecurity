package com.example.lab9.repos;

import com.example.lab9.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepo extends JpaRepository<Department,Integer> {
    boolean existsRecordsByDeptno(int deptno);
}
