package org.example.study.ssafystudy.domain.member.controller.dto;

import org.example.study.ssafystudy.domain.member.entity.MemberEntity;

public record MemberRequest(
        String username,
        String password,
        String nickname) {


    public MemberEntity toEntity(){
        return MemberEntity.create(username, password, nickname);
    }

}



