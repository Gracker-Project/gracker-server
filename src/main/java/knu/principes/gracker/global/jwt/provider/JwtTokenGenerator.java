package knu.principes.gracker.global.jwt.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import knu.principes.gracker.global.jwt.dto.Token;
import knu.principes.gracker.global.jwt.dto.TokenType;
import knu.principes.gracker.global.util.CookieUtil;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenGenerator {
    private final String AUTHORITIES_KEY;
    private final String BEARER_TYPE;
    private final long ACCESS_TOKEN_EXPIRE_TIME;
    private final Key key;

    public JwtTokenGenerator(
            @Value("${spring.jwt.secret}") String secretKey,
            @Value("${spring.jwt.authorities_key}") String authoritiesKey,
            @Value("${spring.jwt.bearer-type}") String bearerType,
            @Value("${spring.jwt.access-token-expire-time}") long accessTokenExpireTime) {

        this.AUTHORITIES_KEY = authoritiesKey;
        this.BEARER_TYPE = bearerType;
        this.ACCESS_TOKEN_EXPIRE_TIME = accessTokenExpireTime;

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Token generateAccessToken(Authentication authentication) {
        String accessToken = doGenerateToken(authentication);
        return Token.builder()
                .grantType(BEARER_TYPE)
                .tokenType(TokenType.ACCESS_TOKEN)
                .token(accessToken)
                .cookie(CookieUtil.createCookie(
                        "Authorization-AccessToken",
                        accessToken,
                        14 * 24 * 60 * 60,
                        "/",
                        true))
                .build();
    }

    private String doGenerateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = System.currentTimeMillis();
        Date tokenExpiresIn = new Date(now + this.ACCESS_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(tokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}

}
