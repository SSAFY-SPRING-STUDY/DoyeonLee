package org.example.study.ssafystudy.domain.member.repository;

import org.example.study.ssafystudy.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {

    // 멤버 변수 저장소
    private final Map<Long, MemberEntity> memberStore= new ConcurrentHashMap<>();

    // 메서드 - 멤버 저장후 반환
    public MemberEntity save(MemberEntity member){
        memberStore.put(member.getId(), member);
        return member;
    }

    // 메서드 - username으로 조회
    // Optonal<MemberEntity>를 사용하는 이유는 NullPointerException을 방지하기 위해서
    public Optional<MemberEntity> findByUsername(String username){

        // 저장소에 있는 값들 중 username이 일치하는 것 찾기
        return memberStore.values()
                .stream()
                .filter(member -> member.getUsername().equals(username))
                .findAny();
    }

    // 메서드 - id로 조회
    public Optional<MemberEntity> findById(Long id){
        return Optional.ofNullable(memberStore.get(id));
    }


}
