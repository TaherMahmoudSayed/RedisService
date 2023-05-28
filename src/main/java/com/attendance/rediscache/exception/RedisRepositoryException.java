package com.attendance.rediscache.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisRepositoryException extends RuntimeException{
    public RedisRepositoryException(String message) {
        super(message);
    }

    public RedisRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
