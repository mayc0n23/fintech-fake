package io.github.mayc0n23.msclientes.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        final String detail = ex.getMessage();
        final ProblemDetails problemDetails = createProblemDetailsBuilder(httpStatus, detail).build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatusCode statusCode, WebRequest request) {

        final HttpStatus httpStatus = HttpStatus.valueOf(statusCode.value());

        if (body == null) {
            body = ProblemDetails.builder()
                    .status(httpStatus.value())
                    .detail(httpStatus.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = ProblemDetails.builder()
                    .status(httpStatus.value())
                    .detail((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ProblemDetails.ProblemDetailsBuilder createProblemDetailsBuilder(HttpStatus httpStatus, String detail) {
        return ProblemDetails.builder()
                .status(httpStatus.value())
                .detail(detail);
    }

}