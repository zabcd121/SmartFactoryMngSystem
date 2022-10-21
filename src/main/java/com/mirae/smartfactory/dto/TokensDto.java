package com.mirae.smartfactory.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class TokensDto {
    private String accessToken;
    private String refreshToken;

}
