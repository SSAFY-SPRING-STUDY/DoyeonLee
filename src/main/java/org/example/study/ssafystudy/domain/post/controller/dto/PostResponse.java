package org.example.study.ssafystudy.domain.post.controller.dto;

import org.example.study.ssafystudy.domain.member.controller.dto.MemberResponse;
import org.example.study.ssafystudy.domain.post.entity.PostEntity;

public record PostResponse(

        Long id,
        String title,
        String content,
        MemberResponse memberResponse

) {
        public static PostResponse from(PostEntity postEntity){

            return new PostResponse(
                    postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getContent(),
                    MemberResponse.from(postEntity.getAuthor())
            );
        }

}
