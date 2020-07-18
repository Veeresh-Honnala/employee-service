package com.employeservice.processor;

import com.employeservice.converters.EmployeeDAOToDTOConverter;
import com.employeservice.converters.EmployeeDTOToDAOConverter;
import com.employeservice.converters.EmployeesToDTOConverter;
import com.employeservice.dao.EmployeeDAO;
import com.employeservice.dto.EmployeeDTO;
import com.employeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeProcessor {
    private EmployeeDAOToDTOConverter employeeDAOToDTOConverter;
    private EmployeeDTOToDAOConverter employeeDTOToDAOConverter;
    private EmployeesToDTOConverter employeesToDTOConverter;
    private EmployeeRepository employeeRepository;

    public Mono<EmployeeDTO> saveEmp(final EmployeeDTO employeeDTO) {
        return Mono.just(employeeDTO)
                .map(employeeDTOToDAOConverter::apply)
                .map(employeeRepository::save)
                .map(employeeDAOToDTOConverter::apply)
                ;
    }

    public Mono<EmployeeDTO> getEmpById(final Integer id) {
        return Mono.just(id)
                .map(employeeRepository::findAllById)
                .map(employeesToDTOConverter::apply)
                .map(lst -> lst.stream().findFirst()
                        .orElseGet(() -> null)); //TODO:Handle Error
    }


}
