package com.mirae.smartfactory.consts;

import lombok.Getter;

@Getter
public enum ConditionCode {
    /**
     * Login, Auth: 01
     */
    LOGIN_SUCCESS("0100", "로그인 성공"),
    ID_NULL_CODE("0101", "아이디를 입력되지 않았습니다."),
    PW_NULL_CODE("0102", "패스워드가 입력되지 않았습니다."),
    NOT_EXIST_ID("0103", "존재하지 않는 아이디입니다."),
    INVALID_PW("0104", "잘못된 비밀번호를 입력하였습니다."),
    LOGOUT_SUCCESS("0105", "로그아웃 성공"),
    WRONG_TYPE_TOKEN("0106", "잘못된 타입의 토큰이 전달되었습니다."),
    EXPIRED_TOKEN("0107", "만료된 토큰이 전달되었습니다."),
    UNSUPPORTED_TOKEN("0108", "지원하지 않는 토큰이 전달되었습니다."),
    ACCESS_DENIED("0109", "접근 거부"),


    /**
     * FurnaceProcess : 02
     */

    FURNACEPROCESS_SEARCH_SUCCESS("0200", "용해일지 조회 성공"),
    FURNACEPROCESS_SEARCH_FAIL("0201", "용해일지 조회 실패"),
    FURNACEPROCESS_SAVE_SUCCESS("0202", "용해일지 저장 성공"),
    FURNACEPROCESS_SAVE_FAIL("0203", "용해일지 조회 실패"),
    FURNACEPROCESS_UPDATE_SUCCESS("0204", "용해일지 수정 성공"),
    FURNACEPROCESS_UPDATE_FAIL("0205", "용해일지 수정 실패"),
    FURNACEPROCESS_DELETE_SUCCESS("0206", "용해일지 삭제 성공"),
    FURNACEPROCESS_DELETE_FAIL("0207", "용해일지 삭제 실패"),


    /**
     * Casting: 03
     */
    CASTING_SEARCH_SUCCESS("0300", "주조일지 조회 성공"),
    CASTING_SEARCH_FAIL("0301", "주조일지 조회 실패"),
    CASTING_SAVE_SUCCESS("0302", "주조일지 저장 성공"),
    CASTING_SAVE_FAIL("0303", "주조일지 저장 실패"),
    CASTING_UPDATE_SUCCESS("0304", "주조일지 수정 성공"),
    CASTING_UPDATE_FAIL("0305", "주조일지 수정 성공"),
    CASTING_DELETE_SUCCESS("0306", "주조일지 삭제 성공"),
    CASTING_DELETE_FAIL("0307", "주조일지 삭제 실패"),


    /**
     * Resource : 04
     */
    OUTERSCRAP_ADD_SUCCESS("0400", "외부스크랩 추가 성공"),
    OUTERSCRAP_ADD_FAIL("0401", "외부스크랩 추가 실패"),
    SI_ADD_SUCCESS("0402", "SI 추가 성공"),
    SI_ADD_FAIL("0403", "SI 추가 실패"),
    INGREDIENT_ADD_SUCCESS("0404", "원자재 추가 성공"),
    INGREDIENT_ADD_FAIL("0405", "원자재 추가 실패"),
    CONTACT_ADD_SUCCESS("0406", "거래처 추가 성공"),
    CONTACT_ADD_FAIL("0407", "거래처 추가 실패"),

    RESOURCE_REMOVE_SUCCESS("0408", "리소스 삭제 성공"),
    RESOURCE_TOTAL_SEARCH_SUCCESS("0409", "리소스 전체 조회"),

    /**
     * Statistics : 05
     */
    TOTAL_DAILY_SEARCH_SUCCESS("0500", "일별 전체 조회 성공"),
    ASHES_DAILY_SEARCH_SUCCESS("0501", "재발생량 일별 조회 성공"),
    ASHES_MONTHLY_SEARCH_SUCCESS("0502", "재발생량 월별 조회 성공"),
    ASHES_QUARTERLY_SEARCH_SUCCESS("0503", "재발생량 분기별 조회 성공"),
    ASHES_YEARLY_SEARCH_SUCCESS("0504", "재발생량 년도별 조회 성공"),
    BILLET_DAILY_SEARCH_SUCCESS("0505", "빌렛 생산량 일별 조회 성공"),
    BILLET_MONTHLY_SEARCH_SUCCESS("0506", "빌렛 생산량 월별 조회 성공"),
    BILLET_QUARTERLY_SEARCH_SUCCESS("0507", "빌렛 생산량 분기별 조회 성공"),
    BILLET_YEARLY_SEARCH_SUCCESS("0508", "빌렛 생산량 년도별 조회 성공"),
    DEFECTIVE_DAILY_SEARCH_SUCCESS("0509", "불량품량 일별 조회 성공"),
    DEFECTIVE_MONTHLY_SEARCH_SUCCESS("0510", "불량품량 월별 조회 성공"),
    DEFECTIVE_QUARTERLY_SEARCH_SUCCESS("0511", "불량품량 분기별 조회 성공"),
    DEFECTIVE_YEARLY_SEARCH_SUCCESS("0512", "불량품량 년도별 조회 성공"),
    MATERIAL_DAILY_SEARCH_SUCCESS("0513", "원자재 사용량 일별 조회 성공"),
    MATERIAL_MONTHLY_SEARCH_SUCCESS("0514", "원자재 사용량 월별 조회 성공"),
    MATERIAL_QUARTERLY_SEARCH_SUCCESS("0515", "원자재 사용량 분기별 조회 성공"),
    MATERIAL_YEARLY_SEARCH_SUCCESS("0516", "원자재 사용량 년도별 조회 성공"),
    ;

    private final String code;
    private final String message;

    ConditionCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
