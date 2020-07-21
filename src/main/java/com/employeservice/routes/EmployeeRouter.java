package com.employeservice.routes;

import com.employeservice.handlers.EmployeeHandler;
import com.employeservice.handlers.ErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;


@Configuration
@RequiredArgsConstructor
public class EmployeeRouter {

    private final EmployeeHandler employeeHandler;
    private final ErrorHandler errorHandler;

    @Bean
    public RouterFunction routes() {

        //TODO:need to handle path not found and other.
        return RouterFunctions
                .nest(RequestPredicates.path("/api"),
                        RouterFunctions.route(RequestPredicates.POST("/employee"), employeeHandler::saveEmployee)
                                .andRoute(RequestPredicates.GET("employee/{empId}"), employeeHandler::findEmployeeById)
                                .andRoute(RequestPredicates.DELETE("/employee/{empId}"), employeeHandler::deleteEmployeeById)
                );

    }
}
