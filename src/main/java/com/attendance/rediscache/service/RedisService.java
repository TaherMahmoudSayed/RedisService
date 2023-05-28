package com.attendance.rediscache.service;

import com.attendance.rediscache.exception.RedisRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.attendance.rediscache.repository.RedisRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {
    private final RedisRepository redisRepository;

    public void save(String key, String value) throws RedisRepositoryException {
        try {

            redisRepository.save(key, value);
        } catch (Exception e) {
            throw new RedisRepositoryException("Error saving key-value pair to Redis", e);
        }
    }

    public String find(String key) throws RedisRepositoryException {
        try {
            String value = redisRepository.find(key);
            if (value == null) {
                throw new RedisRepositoryException("Key not found in Redis");
            }
            return value;
        } catch (Exception e) {
            throw new RedisRepositoryException("Error finding key in Redis", e);
        }
    }

    public void update(String key, String value) throws RedisRepositoryException {
        try {
            if (!redisRepository.isExist(key)) {
                throw new RedisRepositoryException("Key not found in Redis");
            }
            redisRepository.update(key, value);
        } catch (Exception e) {
            throw new RedisRepositoryException("Error updating key-value pair in Redis", e);
        }
    }

    public void delete(String key) throws RedisRepositoryException {
        try {
            if (!redisRepository.isExist(key)) {
                throw new RedisRepositoryException("Key not found in Redis");
            }
            redisRepository.delete(key);
        } catch (Exception e) {
            throw new RedisRepositoryException("Error deleting key from Redis", e);
        }
    }
}
