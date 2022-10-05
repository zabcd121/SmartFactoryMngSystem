package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.model.resource.Member;
import com.mirae.smartfactory.exception.InvalidPWException;
import com.mirae.smartfactory.exception.NotExistIdException;
import com.mirae.smartfactory.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public Member login(String loginId, String password) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        memberOptional.orElseThrow(() -> new NotExistIdException("존재하지 않는 아이디입니다."));

        return memberOptional.filter(m -> m.getPassword().equals(password))
                .orElseThrow(() -> new InvalidPWException("잘못된 비밀번호입니다."));
    }
}
