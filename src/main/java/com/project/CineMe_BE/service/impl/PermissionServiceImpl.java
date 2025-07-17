package com.project.CineMe_BE.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.CineMe_BE.dto.request.PermissionRequest;
import com.project.CineMe_BE.dto.response.PermissionResponse;
import com.project.CineMe_BE.entity.PermissionEntity;
import com.project.CineMe_BE.mapper.request.PermissionRequestMapper;
import com.project.CineMe_BE.mapper.response.PermissionResponseMapper;
import com.project.CineMe_BE.repository.PermissionRepository;
import com.project.CineMe_BE.service.PermissionService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.project.CineMe_BE.constant.CacheName;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionResponseMapper permissionResponseMapper;
    private final PermissionRequestMapper permissionRequestMapper;
    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final LocalizationUtils localizationUtils;
    @Override
    @Cacheable(value = CacheName.PERMISSION , key = "'all'")
    public List<PermissionResponse> getAll() {
        List<PermissionEntity> listPermissions = permissionRepository.findAll();
        return permissionResponseMapper.toListDto(listPermissions);
    }

    @Override
    public PermissionResponse getByKey(String key) {
        //get cache
        String cacheKey = CacheName.PERMISSION + ":" + key;
        String cachedJson = redisTemplate.opsForValue().get(cacheKey);
        if(cachedJson != null){
            try {
                return objectMapper.readValue(cachedJson, PermissionResponse.class);
            } catch (Exception e) {
                throw new RuntimeException("Error reading cached PermissionResponse for key: " + key, e);
            }
        }

        //get from database
        PermissionEntity permission = permissionRepository.findById(key)
                .orElseThrow(() -> new RuntimeException("Permission not found with key: " + key));
        PermissionResponse permissionResponse = permissionResponseMapper.toDto(permission);

        //set cache
        if (permissionResponse != null) {
            try {
                String json = objectMapper.writeValueAsString(permissionResponse);
                redisTemplate.opsForValue().set(cacheKey, json);
                redisTemplate.expire(cacheKey, Duration.ofMinutes(15));
            } catch (Exception e) {
                throw new RuntimeException("Error writing PermissionResponse to cache for key: " + key, e);
            }
        }
        return permissionResponse;
    }

        private void checkKeyExisted(String key){
            if (permissionRepository.existsById(key)) {
                log.warn("Conflict: Permission with key '{}' already exists", key);
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Permission with key already exists");
            }
        }

        @Override
        @CacheEvict(value = CacheName.PERMISSION, key = "'all'")
        public PermissionResponse create(PermissionRequest request) {
            checkKeyExisted(request.getKey());
            PermissionEntity permission = permissionRequestMapper.toEntity(request);
            return permissionResponseMapper.toDto(permissionRepository.save(permission));
        }
}
