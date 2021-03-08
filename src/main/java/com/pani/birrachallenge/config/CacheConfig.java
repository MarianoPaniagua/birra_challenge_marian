package com.pani.birrachallenge.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {
	private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

	@Bean
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();
		manager.setCacheNames(Arrays.asList("users", "meetups"));
		logger.info("Using ConcurrentMapCacheManager as cache");
		return manager;
	}

}
