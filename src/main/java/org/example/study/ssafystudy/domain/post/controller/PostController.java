package org.example.study.ssafystudy.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.domain.auth.component.SessionManager;
import org.example.study.ssafystudy.domain.auth.util.AuthTokenUtils;
import org.example.study.ssafystudy.domain.post.controller.dto.PostRequest;
import org.example.study.ssafystudy.domain.post.controller.dto.PostResponse;
import org.example.study.ssafystudy.domain.post.service.PostService;
import org.example.study.ssafystudy.global.exception.CustomException;
import org.example.study.ssafystudy.global.exception.error.ErrorCode;
import org.example.study.ssafystudy.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final SessionManager sessionManager;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 상태코드 201
    public ApiResponse<PostResponse> createPost(
            @RequestBody PostRequest request,
            @RequestHeader("Authorization") String bearerToken
    ){

        // step 1~3 - 토큰 검증, authorId 추출
        Long authorId = getAuthorIdFromToken(bearerToken);

        // step4 - 서비스 호출, 결과 받기
        PostResponse response = postService.create(request,authorId);
        return ApiResponse.success("게시글 생성에 성공했습니다.",response);
    }


    @GetMapping
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }


    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> getPostById(
            @PathVariable("postId") Long postId){
        return ApiResponse.success("게시글 상세 조회에 성공했습니다.", postService.getPostById(postId));
    }


    @PutMapping("/{postId}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable("postId") Long postId,
            @RequestBody PostRequest request,
            @RequestHeader("Authorization") String bearerToken){

        Long authorId = getAuthorIdFromToken(bearerToken);
        PostResponse response = postService.update(request, postId, authorId);
        return ApiResponse.success("게시글 수정에 성공했습니다.", response);

    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deletePost(
            @PathVariable("postId") Long postId,
            @RequestHeader("Authorization") String bearerToken) {

        Long authorId = getAuthorIdFromToken(bearerToken);
        postService.delete(postId,authorId);

        return ApiResponse.success("게시글 삭제에 성공했습니다.");
    }

    // 새 메서드 생성! - 토큰 인증 유효성 검증 -> sessionKey 추출 -> authorId(memberId) 추출 위한 메서드
    private Long getAuthorIdFromToken(String bearerToken) {
        if(!AuthTokenUtils.isValidBearerToken(bearerToken)){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        return sessionManager.getMemberId(sessionKey);
    }


}
