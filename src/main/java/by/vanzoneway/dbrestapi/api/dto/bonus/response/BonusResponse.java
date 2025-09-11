package by.vanzoneway.dbrestapi.api.dto.bonus.response;

import java.time.LocalDateTime;

public record BonusResponse(
        Long id,
        String name,
        Double discount,
        LocalDateTime releaseDate,
        LocalDateTime expirationDate
) {
}