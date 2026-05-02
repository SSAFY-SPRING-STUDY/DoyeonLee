package org.example.study.ssafystudy.post.service;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.post.controller.dto.PostRequest;
import org.example.study.ssafystudy.post.controller.dto.PostResponse;
import org.example.study.ssafystudy.post.entity.PostEntity;
import org.example.study.ssafystudy.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 1. save - 게시글 생성
    public PostResponse save(PostRequest  postRequest) {
        PostEntity entity = new PostEntity(postRequest.getTitle(), postRequest.getContent(), postRequest.getAuthor());
        return PostResponse.from(postRepository.save(entity));
    }

    // 2. 전체 게시글 조회 - 리스트로 반환.
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    // 3. Id로 게시글 검색, 없으면 에러
    public PostResponse findById(Long postId){
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException(("해당 아이디의 게시글이 없습니다. ")));
        return PostResponse.from(entity);
    }

    // 4. 게시글 수정, 없으면 에러
    public PostResponse update(PostRequest postRequest, Long postId) {
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("업데이트 할 글이 없습니다."));

        return PostResponse.from(entity);
    }

    // 5. id에 해당하는 게시글 삭제
    public void delete(Long postId) {

        postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("삭제할 게시글이 없습니다."));

        postRepository.deleteById(postId);

    }



}
