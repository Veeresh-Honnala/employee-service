package com.employeservice.repository;

import com.employeservice.dao.EmployeeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDAO, Integer> {
    public EmployeeDAO save(final EmployeeDAO employeeDAO);

    public List<EmployeeDAO> findAllById(final Integer id);

}
