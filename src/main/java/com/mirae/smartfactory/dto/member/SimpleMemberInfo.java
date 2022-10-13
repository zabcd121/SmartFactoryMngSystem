package com.mirae.smartfactory.dto.member;

import com.mirae.smartfactory.domain.model.resource.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SimpleMemberInfo {

    private Long memberId;
    private RoleType roleType;
}
