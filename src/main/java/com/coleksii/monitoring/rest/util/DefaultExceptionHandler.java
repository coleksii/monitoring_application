package com.coleksii.monitoring.rest.util;

import com.coleksii.monitoring.config.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class DefaultExceptionHandler {

  /**
   * Custom default exception handler.
   *
   * @param ex incoming exception
   * @param request WebRequest autowired object
   * @return ResponseEntity&lt;ErrorDetails&gt;
   */
  @ExceptionHandler({
      NotFoundException.class,
      IllegalArgumentException.class,
      MethodArgumentNotValidException.class
  })
  public final ResponseEntity<ErrorDetails> handleExceptions(Exception ex, WebRequest request) {
    log.error("Not found exception", ex);
    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;
  }
}
