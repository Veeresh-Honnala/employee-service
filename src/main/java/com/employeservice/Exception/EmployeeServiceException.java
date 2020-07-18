package com.employeservice.Exception;

import com.employeservice.dto.DomainErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.ExchangeStrategies;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeServiceException extends Exception {
    private DomainErrorDTO domainErrorDTO;
}
