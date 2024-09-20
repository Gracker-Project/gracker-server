package knu.principes.gracker.global.jwt.dto;

import jakarta.servlet.http.Cookie;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record Token(
        String grantType,
        String accessToken
) {
}
