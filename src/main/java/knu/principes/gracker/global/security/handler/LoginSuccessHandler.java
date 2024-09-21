package knu.principes.gracker.global.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import knu.principes.gracker.global.jwt.dto.Token;
import knu.principes.gracker.global.jwt.provider.JwtTokenProvider;
import knu.principes.gracker.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onAuthenticationSuccess 성공");
        Token token = jwtTokenProvider.generateToken(authentication);

        Cookie cookie = CookieUtil.createCookie("auth", token.accessToken(), 60 * 60 * 24 * 10, "/", true);
        response.addCookie(cookie);
        response.sendRedirect("http://localhost:52222/"); // redirect to frontend, 조정하기
    }
}
