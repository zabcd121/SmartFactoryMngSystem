package com.mirae.smartfactory.domain.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long memberId;
    private String name;
    private Title title;
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;
    private String loginId;
    private String password;

    private Member(String name, Title title, RoleType roleType, String loginId, String password) {
        this.name = name;
        this.title = title;
        this.roleType = roleType;
        this.loginId = loginId;
        this.password = password;
    }

    public static Member createMember(String name, Title title, RoleType roleType, String loginId, String password) {
        Member member = new Member(name, title, roleType, loginId, password);

        return member;
    }
}
