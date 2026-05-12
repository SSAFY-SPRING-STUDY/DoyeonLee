package org.example.study.ssafystudy.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.domain.member.controller.dto.MemberRequest;
import org.example.study.ssafystudy.domain.member.controller.dto.MemberResponse;
import org.example.study.ssafystudy.domain.member.entity.MemberEntity;
import org.example.study.ssafystudy.domain.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRespository;

    // 메서드 - 회원가입 처리
    // Request dto에서 정보 꺼내 entity 생성 -> 생성된 entity를 repo 통해 저장 -> 저장된 결과를 reponse dto로 반환
    public MemberResponse join(MemberRequest request){

            MemberEntity newMember = request.toEntity();

            MemberEntity savedMember = memberRespository.save(newMember);

            return MemberResponse.from(savedMember);


//        // entity 생성
//        MemberEntity newMember = MemberEntity.create(
//            request.getUsername(),
//            request.getPassword(),
//            request.getNickname()
//
//        );
//
//        // 리포에 저장
//        MemberEntity savedMember = memberRespository.save(newMember);
//
//        // 응답 dto로 변환하여 MemberResponse 반환
//        return new MemberResponse(
//                savedMember.getId(),
//                savedMember.getUsername(),
//                savedMember.getNickname()
//        );

    }

    public MemberResponse getMemberInfo(Long memberId){
        MemberEntity member = memberRespository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return MemberResponse.from(member);

    }
}
