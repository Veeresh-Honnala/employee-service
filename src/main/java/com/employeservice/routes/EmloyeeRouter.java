package com.employeservice.routes;

import com.employeservice.handlers.EmployeeHandler;
import com.employeservice.handlers.ErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;

@Configuration

public class EmloyeeRouter {

    private EmployeeHandler employeeHandler;
    private ErrorHandler errorHandler;

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
