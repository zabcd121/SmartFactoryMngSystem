package com.mirae.smartfactory.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SaveAndUpdateResourceNameDto {

    @NotBlank(message = "리소스 이름이 입력되지 않았습니다.")
    private String resourceName;
}
