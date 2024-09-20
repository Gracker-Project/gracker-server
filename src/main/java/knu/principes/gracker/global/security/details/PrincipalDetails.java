package knu.principes.gracker.global.security.details;

import knu.principes.gracker.domain.student.entity.Student;
import knu.principes.gracker.domain.student.entity.oauth.OAuthUserInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class PrincipalDetails implements OAuth2User {
    private final Student student;
    private final OAuthUserInfo oAuthUserInfo;
    private final Map<String, Object> attributes;

    @Builder
    public PrincipalDetails(Student student, OAuthUserInfo oAuthUserInfo, Map<String, Object> attributes) {
        this.student = student;
        this.oAuthUserInfo = oAuthUserInfo;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (student != null && student.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority(student.getRole().name()));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        }
        return authorities;
    }

    @Override
    public String getName() {
        return student != null ? student.getStudentName() : oAuthUserInfo.getEmail();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public static PrincipalDetails buildPrincipalDetails(StudentService studentService, OAuthUserInfo oAuthUserInfo, OAuth2User oAuth2User) {
        Student student = null;
        if (studentService.isOAuth2UserInfoConnectedToStudent(oAuthUserInfo)) {
            student = studentService.findStudentByOAuth2UserInfo(oAuthUserInfo);
        }

        return PrincipalDetails.builder()
                .student(student)
                .oAuthUserInfo(oAuthUserInfo)
                .attributes(oAuth2User != null ? oAuth2User.getAttributes() : null)
                .build();
    }
}
