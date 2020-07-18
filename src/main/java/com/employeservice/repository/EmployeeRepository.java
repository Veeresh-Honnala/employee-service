package com.employeservice.repository;

import com.employeservice.dao.EmployeeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDAO,Integer> {
}
