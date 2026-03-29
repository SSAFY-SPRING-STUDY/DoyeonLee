package org.example.springstudy.controller.dto;

import lombok.Getter;

@Getter
public class PostRequest {
    private final String title;
    private final String content;
    private final String author;

    // private final로 인해서 생성자 추가 필요.
    public PostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


//    @Override
//    public String toString() {
//        return "CreatePostRequest{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                ", author='" + author + '\'' +
//                '}';
//    }
}

