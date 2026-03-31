package org.example.springstudy.entity;

import lombok.Getter;

@Getter
public class PostEntity {
    // 메모리에다가 게시글 번호를 몇번으로 넣을 것인지 알려줘야 함.
    public static long AUTO_INCREMENT = 1L;

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostEntity(String title, String content, String author) {
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



}
