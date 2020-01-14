package com.mySpringProject.test.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

// DB 접근자. Dao(Data access object)라고 부르기도 합니다.
// JPA 에서는 Repository 라고 부르며 인터페이스로 생성합니다. 기본적인 CRUD 메소드가 자동으로 생성됩니다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // DESC : 내림차순
    List<Posts> findAllDesc();
}
