package com.mirae.smartfactory.config.redis;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("RefreshToken")
public class RefreshRedisToken {
    @Id
    private String memberId;
    private String token;

    @Builder
    private RefreshRedisToken(String memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    static public RefreshRedisToken createToken(String memberId, String token){
        return new RefreshRedisToken(memberId, token);
    }

    public void reissue(String token) {
        this.token = token;
    }
}
