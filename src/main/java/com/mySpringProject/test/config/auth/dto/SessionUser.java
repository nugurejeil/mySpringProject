package com.mySpringProject.test.config.auth.dto;
import com.mySpringProject.test.domain.user.User;
import lombok.Getter;
import java.io.Serializable;

@Getter
// SessionUser 에는 인증된 사용자 정보만 필요합니다.
public class SessionUser implements Serializable{
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
