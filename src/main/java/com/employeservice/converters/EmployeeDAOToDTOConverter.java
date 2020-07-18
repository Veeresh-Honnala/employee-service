package com.employeservice.converters;

import com.employeservice.dao.EmployeeDAO;
import com.employeservice.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmployeeDAOToDTOConverter implements Function<EmployeeDAO, EmployeeDTO> {
    @Override
    public EmployeeDTO apply(EmployeeDAO employeeDAO) {

        return EmployeeDTO.builder()
                .id(employeeDAO.getId())
                .name(employeeDAO.getName())
                .age(employeeDAO.getAge())
                .build();
    }
}
