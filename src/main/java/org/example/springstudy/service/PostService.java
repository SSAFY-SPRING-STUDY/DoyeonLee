package org.example.springstudy.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.controller.dto.PostRequest;
import org.example.springstudy.controller.dto.PostResponse;
import org.example.springstudy.entity.PostEntity;
import org.example.springstudy.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 해당 클래스의 생성자
public class PostService {
    private final PostRepository postRepository;

    // 1.
    public PostResponse save(PostRequest request) {
        PostEntity entity = new PostEntity(request.getTitle(), request.getContent(), request.getAuthor());

        PostEntity savedEntity = postRepository.save(entity); // entity를 받기.

        PostResponse response = new PostResponse(
                savedEntity.getId(), // 컨트롤러가 다시 받아서 사용자에게 전달함
                savedEntity.getTitle(),
                savedEntity.getContent(),
                savedEntity.getAuthor());
        return response;
    }

    // 2.
    public List<PostResponse> findAll() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for (PostEntity entity : entityList){
            PostResponse response = new PostResponse(entity.getId(), // 컨트롤러가 다시 받아서 사용자에게 전달함
                    entity.getTitle(),
                    entity.getContent(),
                    entity.getAuthor());
            responseList.add(response);
        }
        return responseList;
    }

    // 3.
    public PostResponse findById(Long id) {
        Optional<PostEntity> optionalEntity = postRepository.findById(id);

        if (optionalEntity.isPresent()){
            PostEntity entity = optionalEntity.get();
            PostResponse response = new PostResponse(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getContent(),
                    entity.getAuthor()
            );
            return response;
        }
        else {
            throw new IllegalArgumentException("해당 ID의 게시글이 없습니다. ID: " + id);
        }
    }

    // 4. 게시글 수정
    public void update(Long id, PostRequest request) {

        Optional<PostEntity> optionalEntity = postRepository.findById(id);

        if (optionalEntity.isPresent()){
            PostEntity entity = optionalEntity.get();
            entity.update(request.getTitle(), request.getContent());
        }
        else {
            throw new RuntimeException("수정하려는 게시글이 없습니다. ID: " + id);
        }

    }

    // 5. id로 삭제 > 리포 계층에 전달
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
