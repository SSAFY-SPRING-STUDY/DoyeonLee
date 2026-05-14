package org.example.study.ssafystudy.domain.post.controller.dto;

import org.example.study.ssafystudy.domain.member.entity.MemberEntity;
import org.example.study.ssafystudy.domain.post.entity.PostEntity;

// 롬복 미사용
public class PostRequest {
    private String title;
    private String content;


    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;

    }

    public String getTitle() {return title;}
    public String getContent() {return content;}

    // day04 추가 - toEntity 메서드
    public PostEntity toEntity(MemberEntity author){
        return PostEntity.create(this.title,this.content,author);
    }





}
