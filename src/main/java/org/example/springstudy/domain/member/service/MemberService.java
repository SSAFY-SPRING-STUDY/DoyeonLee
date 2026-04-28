package org.example.springstudy.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.domain.member.controller.dto.MemberRequest;
import org.example.springstudy.domain.member.controller.dto.MemberResponse;
import org.example.springstudy.domain.member.entity.MemberEntity;
import org.example.springstudy.domain.member.repository.MemberRespository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRespository memberRespository;

    public MemberResponse save(MemberRequest request){
        MemberEntity entity = MemberRequest.toEntity(request);
        MemberEntity savedEntity = memberRespository.save(entity);

        return MemberResponse.fromEntity(savedEntity);
    }

    public MemberResponse findById(Long memberId) {
        MemberEntity entity = memberRespository.findById(memberId).orElseThrow(
                () -> new RuntimeException("사용자를 찾을 수 없습니다.")
        );
        return MemberResponse.fromEntity(entity);

    }
}
