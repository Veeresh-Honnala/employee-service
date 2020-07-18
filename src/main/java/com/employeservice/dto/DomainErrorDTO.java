package com.employeservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class DomainErrorDTO {
    private String errorId;
    private List<String> errMsg;
    private HttpStatus status;
}