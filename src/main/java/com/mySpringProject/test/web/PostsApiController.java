package com.mySpringProject.test.web;

import com.mySpringProject.test.service.posts.PostsService;
import com.mySpringProject.test.web.dto.PostsResponseDto;
import com.mySpringProject.test.web.dto.PostsSaveRequestDto;
import com.mySpringProject.test.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //RequiredArgsConstructor : 선언된 모든 final 필드가 포함된 생성자를 생성합니다.
@RestController // RestController : 컨트롤러를 JSON을 반환하는 컨트롤러로 만드어줍니다.
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts") // @PutMapping : 데이터를 수정할때 사용한다
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
