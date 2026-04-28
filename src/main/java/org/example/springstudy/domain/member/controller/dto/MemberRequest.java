package org.example.springstudy.domain.member.controller.dto;

// 레코드이기 때문에 별도의 어노테이션 필요 없음.

import org.example.springstudy.domain.member.entity.MemberEntity;

public record MemberRequest(
        String loginId,
        String password,
        String name

        // MemberRequest request가 있다고 치면
        // request.getLoginId() 이런게 다 내장되어 있음.

) {

    public static MemberEntity toEntity(MemberRequest request) {
        return new MemberEntity(
                request.loginId(),
                request.password(),
                request.name());
    }

}
