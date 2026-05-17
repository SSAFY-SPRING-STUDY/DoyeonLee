package org.example.study.ssafystudy.domain.post.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.domain.member.entity.MemberEntity;
import org.example.study.ssafystudy.domain.member.repository.MemberRepository;
import org.example.study.ssafystudy.domain.post.controller.dto.PostRequest;
import org.example.study.ssafystudy.domain.post.controller.dto.PostResponse;
import org.example.study.ssafystudy.domain.post.entity.PostEntity;
import org.example.study.ssafystudy.domain.post.repository.PostRepository;
import org.example.study.ssafystudy.global.exception.CustomException;
import org.example.study.ssafystudy.global.exception.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PostResponse create(PostRequest  postRequest, Long authorId) {
        MemberEntity member = memberRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity entity = postRequest.toEntity(member);

        return PostResponse.from(postRepository.save(entity));
    }

    public List<PostResponse> getAllPosts(){
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostResponse getPostById(Long postId){
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.from(entity);
    }

    @Transactional
    public PostResponse update(PostRequest postRequest, Long id, Long authorId) {
        MemberEntity member = memberRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity entity = postRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));

        if(!entity.getAuthor().getId().equals(member.getId())){
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        entity.update(postRequest.getTitle(), postRequest.getContent());

        return PostResponse.from(entity);
    }

    @Transactional
    public void delete(Long id, Long authorId) {
        MemberEntity member = memberRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity entity = postRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));

        if(!entity.getAuthor().getId().equals(member.getId())){
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        postRepository.delete(entity);

    }
}
