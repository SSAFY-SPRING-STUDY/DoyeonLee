package org.example.study.ssafystudy.domain.post.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.study.ssafystudy.domain.member.entity.MemberEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE) // 기본 생성자 만들어주기
public class PostEntity {
    // 메모리에다가 게시글 번호를 몇번으로 넣을 것인지 알려줘야 함.
    public static long AUTO_INCREMENT = 1L;

    private Long id;
    private String title;
    private String content;
    private MemberEntity author;

    public PostEntity(String title, String content, MemberEntity author) {
        this.id = AUTO_INCREMENT++; // id 값을 직접 받는 것이 아니라, 메모리에 올려놓은 AUTO_INCREMENT 활용.
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 수정 위한 생성자.  - title과 content만 바꿀 수 있게
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // day04 추가: 정적 팩토리 메서드 - create
    public static PostEntity create(String title, String content, MemberEntity author) {
        return new PostEntity(title, content, author);
    }

}
