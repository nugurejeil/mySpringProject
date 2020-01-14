package com.mySpringProject.test.config.auth;
import com.mySpringProject.test.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정들을 활성화시켜줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    http.csrf().disable().headers().frameOptions().disable() //h2-console화면을 사용하기 위해 해당 옵션들을 disable합니다.
    .and()
            .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점입니다. authorizeRequests가 선언 되어야만 antMatchers옵션을 사용할 수 있습니다.
    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**") //antMatchers : 권한 관리 대상을 지정하는 옵션입니다.
            .permitAll() // 전체 열람권한을 줬습니다.
    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER 권한을 가진 사람만 가능합니다.
            .anyRequest().authenticated()//anyRequest : 나머지 URL들을 나타냅니다. authenticated: 인증된 사용자에게만 허용합니다.
    .and()
            .logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정의 시작입니다.
    .and()
            .oauth2Login() // OAuth2 로그인 기능의 시작점입니다.
    .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당합니다.
    .userService(customOAuth2UserService);
    // 소셜 로그인 성공 이후 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록합니다. 리소스서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있습니다.
    }

}
