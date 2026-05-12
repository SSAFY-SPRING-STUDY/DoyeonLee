package org.example.study.ssafystudy.domain.post.service;

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
public class PostService {

    private final PostRepository postRepository;
    // 회원 존재 확인 위해 memberRepo도 필요함.
    private final MemberRepository memberRepository;

    // create - 회원 존재 있는지 확인
    public PostResponse create(PostRequest  postRequest, Long authorId) {
        MemberEntity member = memberRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        // toEntity 메서드  + 정적 팩토리 메서드를 활용
        PostEntity entity = postRequest.toEntity(member);
        return PostResponse.from(postRepository.save(entity));
    }

    public List<PostResponse> getAllPosts(){
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    // getPostById
    public PostResponse getPostById(Long postId){
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.from(entity);
    }

    // update - authorId 파라미터 추가
    public PostResponse update(PostRequest postRequest, Long id, Long authorId) {

        // 회원 존재 확인
        MemberEntity member = memberRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        // 게시글 존재 확인
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));

        if(!entity.getAuthor().getId().equals(member.getId())){
            throw new CustomException(ErrorCode.INVALID_PERMISSION); // 권한 검증 관련 errorCode
        }

        // 검증 완료 후 업데이트
        entity.update(postRequest.getTitle(), postRequest.getContent());
        return PostResponse.from(postRepository.save(entity));
    }

    public void delete(Long id, Long authorId) {

        // 회원 존재 확인
        MemberEntity member = memberRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        // 게시글 존재 확인
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));

        if(!entity.getAuthor().getId().equals(member.getId())){
            throw new CustomException(ErrorCode.INVALID_PERMISSION); // 권한 검증 관련 errorCode
        }

        // 검증 완료 후 삭제
        postRepository.deleteById(id);

    }


}
