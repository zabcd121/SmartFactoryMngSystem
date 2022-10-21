package com.mirae.smartfactory.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;

}
