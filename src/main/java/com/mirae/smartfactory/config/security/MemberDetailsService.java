package com.mirae.smartfactory.config.auth;

import com.mirae.smartfactory.domain.model.resource.Member;
import com.mirae.smartfactory.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * Spring-Security의 유저 인증 처리 과정중 유저객체를 만드는 과정
     * !! 보통 구글링시 UserDetails 클래스를 따로 만들어서 사용하지만 UserDetails 인터페이스를 구현한
     * User 라는 클래스를 시큐리티가 제공해줘서 굳이 만들어주지 않음
     * @param username userId
     * @return UserDetails (security에서 사용하는 유저 정보를 가진 객체)
     * @throws UsernameNotFoundException userId로 유저를 찾지 못했을 경우 발생하는 에러
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("MemberDetailsService loadUserByUsername: 진입");
        Member member = memberRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("userId : " + username + " was not found"));

        return createUserDetails(member);
    }

    private UserDetails createUserDetails(Member member) {
        // 권한 관리 테이블로 만든 깃
        // -> https://github.com/szerhusenBC/jwt-spring-security-demo/blob/master/src/main/java/org/zerhusen/security/model/User.java
        List<SimpleGrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        return new User(member.getLoginId(),
                member.getPassword(),
                grantedAuthorities);
    }

//    @Override
//    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
//        System.out.println("MemberDetailsService loadUserByUsername: 진입");
//        Member member = memberRepository.findByLoginId(loginId)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("`%s`는 잘못된 아이디입니다.", loginId)));
////        if(member == null) throw new UsernameNotFoundException(String.format("User Not Found `%s`", username));
//        log.info("findmember : {}", member);
//
//        return member;
//    }

    public Optional<Member> findMember(String loginId) { return memberRepository.findByLoginId(loginId);}
}
