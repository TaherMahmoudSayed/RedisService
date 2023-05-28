package com.attendance.rediscache.repository;

import java.util.Map;

public interface RedisRepository {
    void save(String key,String value);
    String find(String key);
    void update(String key,String value);
    void delete(String key);
    boolean isExist(String Key);
}
