package knu.principes.gracker.global.jwt.provider;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import knu.principes.gracker.domain.student.entity.oauth.OAuthUserInfo;
import knu.principes.gracker.global.security.details.PrincipalDetails;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenValidator {
    private final String AUTHORITIES_KEY;
    private final Key key;

    public JwtTokenValidator(
            @Value("${spring.jwt.secret}") String secretKey,
            @Value("${spring.jwt.authorities_key}") String authoritiesKey
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.AUTHORITIES_KEY = authoritiesKey;
    }

    public Authentication getAuthentication(String accessToken, StudentService studentService) {
        Claims claims = parseClaims(accessToken);
        Collection<? extends GrantedAuthority> authorities = getAuthoritiesFromClaims(claims);

        OAuthUserInfo oAuthUserInfo;
        try {
            oAuthUserInfo = studentService.findOAuth2UserInfoByEmail(claims.getSubject());
        } catch (OAuth2UserInfoNotFoundException e) {
            // Handle the case where OAuthUserInfo is not found
            oAuthUserInfo = OAuthUserInfo.builder()
                    .email(claims.getSubject())
                    .build();
        }

        PrincipalDetails principalDetails = PrincipalDetails.buildPrincipalDetails(studentService, oAuthUserInfo, null);
        return new UsernamePasswordAuthenticationToken(principalDetails, "", authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        String authoritiesStr = claims.get(AUTHORITIES_KEY, String.class);
        if (authoritiesStr == null || authoritiesStr.isEmpty()) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        }

        return Arrays.stream(authoritiesStr.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public static String resolveToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization-AccessToken".equals(cookie.getName())) {
                    return cookie.getValue().replace("Bearer", "").trim();
                }
            }
        }
        return null;
    }
}
