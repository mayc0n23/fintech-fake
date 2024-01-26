package io.github.mayc0n23.msavaliadorcredito.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class ProblemDetails {

    private final Integer status;

    private final String detail;

    private final OffsetDateTime timestamp;

    @Builder
    @Getter
    public static class Object {
        private String name;
        private String message;
    }

}