package com.system.remedios.Exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@SuperBuilder
public class ExceptionDetails {
    private String title;
    protected int status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;

}
