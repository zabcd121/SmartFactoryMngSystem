package com.mirae.smartfactory.domain.resource;

//ADMIN: 관리자
//SUPERVISOR: 시스템에 기록을 등록하는 사람
//OPERATOR: 현장 작업자. 현장 작업자가 작성한 내용을 SUPERVISOR가 시스템에 입력
//MEMBER: 일반회원
public enum RoleType {
    ROLE_MEMBER, ROLE_ADMIN, ROLE_SUPERVISOR, ROLE_OPERATOR
}
