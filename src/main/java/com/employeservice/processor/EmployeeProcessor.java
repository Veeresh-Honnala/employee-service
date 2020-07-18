package com.employeservice.processor;

import com.employeservice.Exception.EmployeeServiceException;
import com.employeservice.converters.EmployeeDAOToDTOConverter;
import com.employeservice.converters.EmployeeDTOToDAOConverter;
import com.employeservice.converters.EmployeesToDTOConverter;
import com.employeservice.dao.EmployeeDAO;
import com.employeservice.dto.DomainErrorDTO;
import com.employeservice.dto.EmployeeDTO;
import com.employeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

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
                .onErrorResume(err -> Mono.error(EmployeeServiceException.builder()
                        .domainErrorDTO(DomainErrorDTO.builder()
                                .errorId("001")
                                .errMsg(Collections.singletonList(err.getMessage()))
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .build())
                        .build()))
                ;
    }

    public Mono<EmployeeDTO> findEmpById(final Integer id) {
        return Mono.just(id)
                .map(employeeRepository::findAllById)
                .map(employeesToDTOConverter::apply)
                .map(lst -> lst.stream().findFirst()
                        .orElse( null))
                .onErrorResume(err -> Mono.error(EmployeeServiceException.builder()
                        .domainErrorDTO(DomainErrorDTO.builder()
                                .errorId("002")
                                .errMsg(Collections.singletonList(err.getMessage()))
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .build())
                        .build()));
    }


}
