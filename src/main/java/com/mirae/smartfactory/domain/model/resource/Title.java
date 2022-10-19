package com.mirae.smartfactory.domain.model.resource;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)

//용해일지:
// 담당자(Representative)
// 반장(Staff)
// 주임(Associate)
// 과장(Section Head)
// 이사(Director)
// 대표이사(Chairman)

//주조일지:
// 담당자(Representative)
// 계장(Chief)
// 과장(Section Head)
// 이사(Director)
// 사장(President)

public enum Title {
    CHAIRMAN("대표이사"),
    DIRECTOR("이사"),
    PRESIDENT("사장"),
    SECTION_HEAD("과장"),
    CHIEF("계장"),
    ASSOCIATE("주임"),
    STAFF("반장"),
    REPRESENTATIVE("담당자");

    final private String krTitle;
    private Title(String krTitle){
        this.krTitle = krTitle;
    }
    public String getTitle(){
        return this.krTitle;
    }
}
