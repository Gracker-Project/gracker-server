package knu.principes.gracker.global.security.details;

import knu.principes.gracker.domain.student.entity.Role;
import knu.principes.gracker.domain.student.entity.Student;
import knu.principes.gracker.domain.student.repository.StudentRepository;
import knu.principes.gracker.global.security.dto.GoogleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final StudentRepository studentRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

        if (registrationId.equals("google")) {
            GoogleResponse googleResponse = new GoogleResponse(oAuth2User.getAttributes());
            String email = googleResponse.getEmail();
            Optional<Student> accountOptional = studentRepository.findByEmail(email);

            Student student;

            if (accountOptional.isPresent()) {
                student = accountOptional.get();
            }
            else {
                student = Student.builder()
                        .email(email)
                        .studentName(googleResponse.getName())
                        .role(Role.ROLE_STUDENT)
                        .build();
                studentRepository.save(student);
            }
            return new PrincipalDetails(student, googleResponse);
        }
        else {
            throw new OAuth2AuthenticationException("Unsupported registrationId: " + registrationId);
        }
    }
}
