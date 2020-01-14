package com.mySpringProject.test.config.auth;
import com.mySpringProject.test.config.auth.dto.OAuthAttributes;
import com.mySpringProject.test.config.auth.dto.SessionUser;
import com.mySpringProject.test.domain.user.User;
import com.mySpringProject.test.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
// 여기서는 구글 로그인 이후 가져온 사용자의 정보들을 기반으로 가입 및 정보 수정, 세션 저장 등의 기능을 지원합니다.
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행중인 서비스를 구분하는 코드입니다. 네이버 로그인인지 구글 로그인인지 구분하는 역할을 합니다.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName(); //OAuth2 로그인 진행 시 키가 되는 필드값입니다. primary key와 같습니다.

        // OAuth2UserService 를 통해 가져온 OAuth2User의 attribute를 담을 클래스입니다. 이후 다른 소셜 로그인에서도 이 클래스를 사용합니다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);

        // 세션에 사용자 정보를 저장하기 위한 Dto 클래스입니다. 로그인 성공 시 세션에 SessionUser를 저장합니다.
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),attributes.getNameAttributeKey()
        );
    }

    // 사용자 정보가 업데이트 되었을 때를 대비해 update 기능도 구현했습니다. 사용자의 이름이나 사진이 변경되면 User 엔티티에도 반영됩니다.
    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
