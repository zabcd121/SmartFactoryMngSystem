package com.mirae.smartfactory.config.redis;

import org.springframework.data.repository.CrudRepository;

public interface RefreshRedisRepository extends CrudRepository<RefreshRedisToken, String> {
}
