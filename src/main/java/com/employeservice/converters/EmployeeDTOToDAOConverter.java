package com.employeservice.converters;

import com.employeservice.dao.EmployeeDAO;
import com.employeservice.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmployeeDTOToDAOConverter implements Function<EmployeeDTO, EmployeeDAO> {
    @Override
    public EmployeeDAO apply(EmployeeDTO employeeDTO) {
        return null;
    }
}
