package org.example.study.ssafystudy.member.entity;

import lombok.Getter;
@Getter
public class MemberEntity {

    // 필드 구성
    private Long id;
    private String username;
    private String password;
    private String nickname;

    // 정적 필드: id 자동증가
    private static long AUTO_INCREMENT = 1L;


    // private 생성자
    private MemberEntity(Long id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    // 객체 생성 시 id 자동 할당
    public static MemberEntity create(String username, String password, String nickname) {
        return new MemberEntity(AUTO_INCREMENT++, username, password, nickname);
    }

    // 비밀번호 일치 확인하는 메서드
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

}
