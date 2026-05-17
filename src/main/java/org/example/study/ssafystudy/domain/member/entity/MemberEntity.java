package org.example.study.ssafystudy.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;


    // private 생성자
    private MemberEntity(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    // 객체 생성 시 id 자동 할당
    public static MemberEntity create(String username, String password, String nickname) {
        return new MemberEntity(username, password, nickname);
    }

    // 비밀번호 일치 확인하는 메서드
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

}
