package com.mySpringProject.test.domain.posts;

import org.junit.After; // 단위 테스트가 끝날때마다 수행되는 메소드를 지정합니다.
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "test post";
        String content = "test content";

        postsRepository
                // 테이블 posts에 insert/update 쿼리를 실행합니다. id 값이 있다면 update, 없다면 insert 쿼리가 실행됩니다.
                .save(Posts.builder()
                .title(title)
                .content(content)
                .author("nugurejeil1@gmail.com")
                .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll(); // 테이블의 모든 데이터를 조회합니다.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2020,1,13,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate=" +  posts.getCreatedDate() + ", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate().isAfter(now));

    }
}
