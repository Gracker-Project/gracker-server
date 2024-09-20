package knu.principes.gracker.global.security.details;

import knu.principes.gracker.domain.student.entity.Student;
import knu.principes.gracker.global.security.dto.GoogleResponse;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetails implements OAuth2User {


    private final Student student;
    private GoogleResponse googleResponse;

    public PrincipalDetails(Student student, GoogleResponse googleResponse) {
        this.student = student;
        this.googleResponse = googleResponse;
    }

    public PrincipalDetails(Student student) {
        this.student = student;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return googleResponse.attribute();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return student.getRole().name();
            }
        });
        return collection;    }

    @Override
    public String getName() {
        return student.getEmail();
    }
}
