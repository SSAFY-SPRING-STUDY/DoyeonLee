package org.example.study.ssafystudy.domain.post.repository;

import org.example.study.ssafystudy.domain.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    List<PostEntity> postList = new ArrayList<>();

    // 메서드 - 저장후 eneity 반환
    public PostEntity save(PostEntity postEntity) {
        postList.add(postEntity);
        return postEntity;
    }

    //
    public List<PostEntity> findAll() {
        return postList;
    }

    //
    public Optional<PostEntity> findById(Long postId) {
        for  (PostEntity post : postList) {
            if(post.getId().equals(postId)){
                return Optional.of(post);
            }
        }

        return Optional.empty();
    }

    public void deleteById(Long postId) {
        for (int i = 0; i < postList.size(); i++) {
            PostEntity post = postList.get(i);
            if (post.getId().equals(postId)) {
                postList.remove(i);
                break;
            }
        }

    }

}
