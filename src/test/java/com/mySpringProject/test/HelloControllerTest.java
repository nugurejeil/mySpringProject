package com.mySpringProject.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //JUnit 과 SpringRunner 스프링 실행자를 실행합니다.
@WebMvcTest
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; // 웹 API를 테스트합니다. 스프링 MVC 테스트의 시작점입니다.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 get 요청을 합니다.
                .andExpect(status().isOk()) // mvc.perform 의 결과를 검증합니다. isOk는 get 의 결과로 200을 받았는지 검증합니다.
                .andExpect(content().string(hello)); // HelloController 에서 hello를 반환하기 때문에 이 값이 맞는지 검증합니다.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name).param("amount", String.valueOf(amount))) // API테스트할 때 사용될 요청 파라메터를 설정합니다. 값은 String 만 허용됩니다.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // JSON 응답값을 필드별로 검증할 수 있는 메서드입니다.
                .andExpect(jsonPath("$.amount", is(amount))); // $를 기준으로 필드명을 명시합니다.
    }
}
