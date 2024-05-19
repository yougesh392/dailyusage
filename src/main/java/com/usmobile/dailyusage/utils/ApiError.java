package com.usmobile.dailyusage.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "API error response")
public class ApiError {
    @Schema(description = "HTTP status code", example = "xxx")
    private int status;
    @Schema(description = "Error message")
    private String message;
}
