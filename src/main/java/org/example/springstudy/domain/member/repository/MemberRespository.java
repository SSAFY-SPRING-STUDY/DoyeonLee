package org.example.springstudy.domain.member.repository;

import org.example.springstudy.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRespository {

    private static Map<Long, MemberEntity> memberStore = new ConcurrentHashMap<>();

    public MemberEntity save(MemberEntity entity) {
        memberStore.put(entity.getId(), entity);
        MemberEntity savedEntity = memberStore.get(entity.getId());

        return savedEntity;
    }

    //
    public Optional<MemberEntity> findByLoginId(String loginId) {
        for(MemberEntity entity : memberStore.values()){
            if(entity.getLoginId().equals(loginId))
                return Optional.of(entity);
        }
        return Optional.empty();
    }

    public Optional<MemberEntity> findById(Long memberId) {
        return Optional.ofNullable(memberStore.get(memberId));
    }
}
