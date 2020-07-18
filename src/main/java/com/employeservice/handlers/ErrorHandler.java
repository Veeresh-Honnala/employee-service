package com.employeservice.handlers;

import com.employeservice.Exception.EmployeeServiceException;
import org.omg.CORBA.ServerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Optional;

@Component
public class ErrorHandler {
    public Mono<ServerResponse> notFound(final ServerRequest serverRequest) {
        return ServerResponse.status(HttpStatus.NOT_FOUND).build()
                ;
    }

    public Mono<ServerResponse> methodNotAllowed(final ServerRequest serverRequest) {
        return ServerResponse.status(HttpStatus.METHOD_NOT_ALLOWED).build()
                ;
    }

    public Mono<ServerResponse> handleError(final Throwable exception) {
        return Optional.of(exception)
                .filter(ex -> ex instanceof EmployeeServiceException)
                .map(ex -> (EmployeeServiceException) ex)
                .map(ex -> ServerResponse.status(ex.getDomainErrorDTO().getStatus())
                        .body(BodyInserters.fromValue(ex.getDomainErrorDTO())))
                .orElseGet(() -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(BodyInserters.fromValue(Collections.singleton("Internal Technical Error"))))
                ;
    }
}
