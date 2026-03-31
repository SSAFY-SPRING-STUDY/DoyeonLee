package org.example.springstudy.repository;

import org.example.springstudy.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // 저장소의 역할, My
public class PostRepository {
    List<PostEntity> postList = new ArrayList<>();

    // 1. 저장
    public PostEntity save(PostEntity postEntity){
        postList.add(postEntity);
        return postEntity;
    }

    // 2. 조회
    public List<PostEntity> findAll() {
        return postList;
    }

    // 3. id로 조회
    public Optional<PostEntity> findById(Long id){
        // 저장된 리스트 돌면서, 하나씩 확인
        for (PostEntity post : postList){
            if (post.getId().equals(id)){
                return Optional.of(post);
            }
        }
        return Optional.empty(); // 예외처리 > 비어있는 상태 반환
    }

    // 5. id로 리스트에서 삭제 -> 삭제하려면 2번
    public void deleteById(Long id){
        for (int i = 0; i < postList.size(); i++) {
            PostEntity post = postList.get(i);
            if (post.getId().equals(id)) {
                postList.remove(i);
                break;
            }
        }
    }
}
