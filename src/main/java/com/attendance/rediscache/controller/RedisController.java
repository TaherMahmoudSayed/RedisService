package com.attendance.rediscache.controller;

import com.attendance.rediscache.exception.RedisRepositoryException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.attendance.rediscache.service.RedisService;

import java.util.Map;

@RestController
@RequestMapping("/api/redis")
@AllArgsConstructor

public class RedisController {
    private final RedisService redisService;
    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Map<String, String> keyValue) {
        try {
            String key = keyValue.get("key");
            String value = keyValue.get("value");
            redisService.save(key, value);
            return ResponseEntity.ok("Key-value pair saved successfully");
        } catch (RedisRepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving key-value pair to Redis: " + e.getMessage());
        }
    }


    @GetMapping("/{key}")
    public ResponseEntity<String> find(@PathVariable String key) {
        try {
            String value = redisService.find(key);
            return ResponseEntity.ok(value);
        } catch (RedisRepositoryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Key not found in Redis: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Map<String,String>keyValue) {
        try {
            String key = keyValue.get("key");
            String value = keyValue.get("value");
            redisService.update(key, value);
            return ResponseEntity.ok("Key-value pair updated successfully");
        } catch (RedisRepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating key-value pair in Redis: " + e.getMessage());
        }
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> delete(@PathVariable String key) {
        try {
            redisService.delete(key);
            return ResponseEntity.ok("Key deleted successfully");
        } catch (RedisRepositoryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Key not found in Redis: " + e.getMessage());
        }
    }


}
