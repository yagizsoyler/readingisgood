package com.getir.interview.readingisgood.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
class ApiError
{
    private HttpStatus status;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss" )
    private LocalDateTime timestamp;
    private String message;
}