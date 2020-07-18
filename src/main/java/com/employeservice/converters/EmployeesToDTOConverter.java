package com.employeservice.converters;

import com.employeservice.dao.EmployeeDAO;
import com.employeservice.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeesToDTOConverter implements Function<List<EmployeeDAO>, List<EmployeeDTO>> {

    private final EmployeeDAOToDTOConverter employeeDAOToDTOConverter;

    @Override
    public List<EmployeeDTO> apply(final List<EmployeeDAO> employeeDAOS) {
        return employeeDAOS.stream()
                .map(employeeDAOToDTOConverter::apply)
                .collect(Collectors.toList());
    }
}
