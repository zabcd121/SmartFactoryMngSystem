package com.mirae.smartfactory.config.auth;

import com.mirae.smartfactory.domain.resource.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberDetails  implements UserDetails {

    private Member member;

    @Override
    public String getPassword() {
        log.info("password= {}", member.getPassword());
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        member.getRoleType()(r -> {
//            authorities.add(()-> r);
//        });

        authorities.add(new SimpleGrantedAuthority(member.getRoleType().toString()));
        log.info("authorities= {}", authorities);
        return authorities;
    }
}
