package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.resource.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return Optional.ofNullable(em.createQuery(
                        "select m from Member m" +
                                " where m.loginId = :loginId", Member.class
                ).setParameter("loginId", loginId)
                .getResultStream().findFirst().orElse(null)
        );
    }
}
