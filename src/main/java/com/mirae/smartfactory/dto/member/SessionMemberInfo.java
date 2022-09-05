package com.mirae.smartfactory.dto.member;

import com.mirae.smartfactory.domain.resource.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SessionMemberInfo {

    private Long memberId;
    private RoleType roleType;
}
