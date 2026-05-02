package org.example.study.ssafystudy.post.controller.dto;

import org.example.study.ssafystudy.post.entity.PostEntity;

public record PostResponse(

        Long id,
        String title,
        String content,
        String author

) {
        public static PostResponse from(PostEntity postEntity){

            return new PostResponse(
                    postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getContent(),
                    postEntity.getAuthor()
            );
        }

}
