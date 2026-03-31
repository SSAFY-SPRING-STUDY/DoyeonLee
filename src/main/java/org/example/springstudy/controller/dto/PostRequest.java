package org.example.springstudy.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequest {
    private final String title; // 게시글 제목
    private final String content; // 게시글 본문
    private final String author; // 작성자 .
    // 서버가 id는 자동생성하므로 필요 없음.

}

