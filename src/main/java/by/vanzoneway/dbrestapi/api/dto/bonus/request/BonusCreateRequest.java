package by.vanzoneway.dbrestapi.api.dto.bonus.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record BonusCreateRequest(
        @NotBlank String name,
        @Positive Double discount,
        LocalDateTime releaseDate,
        LocalDateTime expirationDate
) {
}
