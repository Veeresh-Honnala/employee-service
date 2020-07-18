package com.employeservice.handlers;

import com.employeservice.dto.EmployeeDTO;
import com.employeservice.processor.EmployeeProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeHandler {
    private final EmployeeProcessor employeeProcessor;
    private final ErrorHandler errorHandler;

    public Mono<ServerResponse> saveEmployee(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(EmployeeDTO.class)
                .flatMap(employeeProcessor::saveEmp)
                .flatMap(emp -> ServerResponse.ok().body(BodyInserters.fromValue(emp)))
                .onErrorResume(err -> errorHandler.handleError(err));
    }

    public Mono<ServerResponse> findEmployeeById(final ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("empId"))
                .flatMap(id -> employeeProcessor.findEmpById(Integer.parseInt(id)))
                .flatMap(emp -> ServerResponse.ok().body(BodyInserters.fromValue(emp)))
                .onErrorResume(err -> errorHandler.handleError(err))
        ;
    }

    public Mono<ServerResponse> deleteEmployeeById(final ServerRequest serverRequest) {
        return null
                ;
    }
}
