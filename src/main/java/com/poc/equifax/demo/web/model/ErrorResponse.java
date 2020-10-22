package com.poc.equifax.demo.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ErrorResponse {
    @NotNull
    private Instant timestamp;

    @NotNull
    private String message;
}
