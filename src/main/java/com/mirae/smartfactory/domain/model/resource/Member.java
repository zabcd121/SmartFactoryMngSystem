package com.mirae.smartfactory.domain.model.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long memberId;
    private String name;

    @Enumerated(value = EnumType.STRING)
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

//    @Override
//    public String getPassword() {
//        log.info("password= {}", this.password);
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.loginId;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
////        member.getRoleType()(r -> {
////            authorities.add(()-> r);
////        });
//
//        authorities.add(new SimpleGrantedAuthority(this.roleType.toString()));
//        log.info("authorities= {}", authorities);
//        return authorities;
//    }


    public List<String> getAuthorities() {
        List roleList = new ArrayList<>();

        if(!roleType.equals("")) {
            roleList.add(roleType.toString());
        }
        return roleList;
    }
}
