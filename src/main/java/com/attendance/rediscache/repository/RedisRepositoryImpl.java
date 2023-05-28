package com.attendance.rediscache.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RedisRepositoryImpl implements RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private ValueOperations<String, String> redisOperations;
    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        log.info("looooooooog"+this.redisTemplate.toString());
    }
    @PostConstruct
    public void init(){
        redisOperations = this.redisTemplate.opsForValue();
        log.info("init Loooog "+ redisTemplate.toString());

    }

    @Override
    public void save(String key, String value) {
        redisOperations.set(key,value);

    }

    @Override
    public String find(String key) {
        String value=redisOperations.get(key);
        return value;
    }

    @Override
    public void update(String key, String value) {
        redisOperations.set(key,value);
    }

    @Override
    public void delete(String key) {
        redisOperations.getAndDelete(key);

    }

    @Override
    public boolean isExist(String Key) {
        return Boolean.TRUE.equals(redisOperations.getOperations().hasKey(Key));
    }
}
