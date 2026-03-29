package org.example.springstudy.controller.dto;

import lombok.Getter;

@Getter
public class PostResponse {
    private final Long id; // 게시글이 몇번째 게시글인지 관리 (서버 측면에서 관리)
    private final String title;
    private final String content;
    private final String author;


    public PostResponse(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
