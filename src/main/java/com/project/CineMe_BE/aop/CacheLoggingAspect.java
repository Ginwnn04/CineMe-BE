package com.project.CineMe_BE.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CacheLoggingAspect {

    @Before("@annotation(cacheable)")
    public void logBeforeCacheable(JoinPoint joinPoint, Cacheable cacheable) {
        log.info("🔍 Trying to read from cache: {}", cacheable.value()[0]);
    }

    @AfterReturning("@annotation(cachePut)")
    public void logAfterPut(JoinPoint joinPoint, CachePut cachePut) {
        log.info("📥 Cache PUT executed: {}", cachePut.value()[0]);
    }

    @After("@annotation(cacheEvict)")
    public void logAfterEvict(JoinPoint joinPoint, CacheEvict cacheEvict) {
        if (cacheEvict.allEntries()) {
            log.info("🧹 Cache CLEARED all entries: {}", cacheEvict.value()[0]);
        } else {
            log.info("🗑️ Cache EVICT executed: {}", cacheEvict.value()[0]);
        }
    }
}