package org.example.springstudy.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.controller.dto.PostRequest;
import org.example.springstudy.controller.dto.PostResponse;
import org.example.springstudy.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // 이 클래스의 생성자를 롬복이 알아서 만들어줌.
public class PostController {
    private final PostService postService;

    // CRUD 메서드들 -------------------------------------------
    // 1. 게시글 생성
    @PostMapping("/api/posts") // 이 url로 post요청이 오면 보내줌 + 클래스를 스프링이 관리중 >> 어노테이션 필요
    public PostResponse createPost(@RequestBody PostRequest request){
        PostResponse response = postService.save(request); // 서비스의 save 메서드가 반환한 객체를 받아서 반환
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

    // 4. 게시글 수정 - put
    @PutMapping("/api/posts/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody PostRequest request){
        postService.update(id, request);
        return "ID " + id + " : 게시글이 성공적으로 수정되었습니다.";
    }

    // 5. 게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public String deletePost(@PathVariable Long id){
        postService.delete(id);
        return "ID " + id + " : 게시글이 성공적으로 삭제되었습니다.";
    }

}
