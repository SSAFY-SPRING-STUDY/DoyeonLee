package org.example.study.ssafystudy.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.post.controller.dto.PostRequest;
import org.example.study.ssafystudy.post.controller.dto.PostResponse;
import org.example.study.ssafystudy.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 1. 게시글 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest request){
        return postService.save(request);
    }

    // 2. 전체 게시글 조회
    @GetMapping
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }

    // 3. id 기반, post 하나 조회
    // Q. 명세서에는 PostService의 findById는 PostResponse를 반환하는데
    // PostController에서는 ResponseEntity<PostResponse>를 반환하는 이유?
    // A. 상태 코드를 명확하게 지정할 수 있고, 헤더 설정에 유리하다.
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(
            @PathVariable("postId") Long postId){

        PostResponse response = postService.findById(postId);

        return ResponseEntity.ok(response) ;
    }

    // 4. post 업뎃 하기
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable("postId") Long postId,
            @RequestBody PostRequest request){

        PostResponse response = postService.findById(postId);

        return ResponseEntity.ok(response);

    }

    // 5. id에 해당하는 post 삭제
    // 어노테이션 작성시주의할 것.
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable("postId") Long postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }




}
