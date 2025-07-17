package com.project.CineMe_BE.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.CineMe_BE.constant.CacheName;
import com.project.CineMe_BE.dto.request.RoleRequest;
import com.project.CineMe_BE.dto.response.RoleResponse;
import com.project.CineMe_BE.entity.RoleEntity;
import com.project.CineMe_BE.mapper.request.RoleRequestMapper;
import com.project.CineMe_BE.mapper.response.RoleResponseMapper;
import com.project.CineMe_BE.repository.RoleRepository;
import com.project.CineMe_BE.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleResponseMapper roleResponseMapper;
    private final RoleRequestMapper roleRequestMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
//    @Cacheable(value = CacheName.ROLE, key = "#id") // auto get and set cache
    private RoleEntity getRoleEntityRaw(UUID id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

    }

    @Override
    @Cacheable(value = CacheName.ROLE , key = "'all'") // auto get and set cache
    public List<RoleResponse> getAllRoles() {
        List<RoleEntity> listRoles = roleRepository.findAll();
        return roleResponseMapper.toListDto(listRoles);
    }

    @Override
//    @Cacheable(value = CacheName.ROLE , key = "#id") // auto get and set cache
    public RoleResponse getRoleById(UUID id) {
        //Get Cache
        String cacheKey = CacheName.ROLE + ":" + id;
        String cachedJson = redisTemplate.opsForValue().get(cacheKey);

        if ( cachedJson != null ){
            try {
                return objectMapper.readValue(cachedJson, RoleResponse.class);
            }
            catch (Exception e) {
                throw new RuntimeException("Error reading cached RoleResponse for id: " + id, e);
            }
        }

        //if not found in cache, get from database
        RoleEntity role = getRoleEntityRaw(id);
        RoleResponse response = roleResponseMapper.toDto(role);

        //Set Cache
        try {
            String json = objectMapper.writeValueAsString(response);
            redisTemplate.opsForValue().set(cacheKey, json, Duration.ofSeconds(60)); // Set cache with TTL 15 mins
        }
        catch (Exception e) {
            throw new RuntimeException("Error writing RoleResponse to cache for id: " + id, e);
        }

        return response;
    }

    @Override
    @CacheEvict(value = CacheName.ROLE, key = "'all'") // auto get and set cache
    public RoleResponse createRole(RoleRequest request) {
        RoleEntity roleEntity = roleRequestMapper.toEntity(request);
        roleRepository.save(roleEntity);
        return roleResponseMapper.toDto(roleEntity);
    }
}
