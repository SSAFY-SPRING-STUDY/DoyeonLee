package org.example.springstudy.controller;

import org.example.springstudy.controller.dto.PostRequest;
import org.example.springstudy.controller.dto.PostResponse;
import org.example.springstudy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired  //-> 생략 가능 (생성자로 의존성 주입)
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 1. 게시글 생성
    @PostMapping("/api/posts") // 이 url로 post요청이 오면 보내줌 + 클래스를 스프링이 관리중 >> 어노테이션 필요
    public PostResponse createPost(@RequestBody PostRequest request){
        PostResponse response = postService.save(request);

        return response;
    }

    //2. 전체 목록 조회
    @GetMapping("/api/posts")
    public List<PostResponse> findAllPosts(){
        return postService.findAll();
    }

    //3. id 기반 조회
    @GetMapping("/api/posts/{id}") // id 값을 받아오는 로직
    public PostResponse findPostById(@PathVariable Long id){
        PostResponse response3 = postService.findById(id);
        return response3;
    }

    // 4. 게시글 수정
    @PutMapping("/api/posts/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody PostRequest request){
        postService.update(id, request);
        return "ID " + id + "게시글이 성공적으로 수정되었습니다.";
    }

    // 5. 게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public String deletePost(@PathVariable Long id){
        postService.delete(id);
        return "ID " + id + "게시글이 성공적으로 삭제되었습니다.";
    }

}
