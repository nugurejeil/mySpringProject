package com.mySpringProject.test.web;

import com.mySpringProject.test.config.auth.LoginUser;
import com.mySpringProject.test.config.auth.dto.SessionUser;
import com.mySpringProject.test.service.posts.PostsService;
import com.mySpringProject.test.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
//    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있습니다.
        // postsService.findAllDesc() 로 가져온 결과를 posts로 index.mustache에 전달합니다.
        model.addAttribute("posts", postsService.findAllDesc());

        //로그인에 성공하고 CustomOAuth2UserService 클래스에서 세션에 저장한 SessionUser 정보를 가져옵니다.
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){ // 세션에 유저정보가 없다면 로그인 전이니 값을 등록하지 않습니다.
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
