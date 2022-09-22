package com.mirae.smartfactory.config.auth;

import com.mirae.smartfactory.domain.resource.Member;
import com.mirae.smartfactory.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService loadUserByUsername: 진입");
        Member member = memberRepository.findByIdInAuthorization(Long.parseLong(userPk))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("`%s`는 잘못된 아이디입니다.", userPk)));
//        if(member == null) throw new UsernameNotFoundException(String.format("User Not Found `%s`", username));
        log.info("findmember : {}", member);

        return new MemberDetails(member);
    }
}
