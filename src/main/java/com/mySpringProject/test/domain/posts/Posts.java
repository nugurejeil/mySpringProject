package com.mySpringProject.test.domain.posts;

import com.mySpringProject.test.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스내 모든 필드의 getter 메서드 자동 생성
@NoArgsConstructor // 기본 생성자 자동추가
@Entity // 테이블과 링크될 클래스임을 나타냅니다.
public class Posts extends BaseTimeEntity {
    @Id //해당 테이블의 프라이머리 키 필드를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프라이머리 키 생성규칙입니다. GenerationType.IDENTITY을 넣어줘야 자동 증가가 적용됩니다.
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼입니다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성합니다.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
