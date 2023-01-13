//package com.dxj.config;
//
//import com.dxj.utils.FastJsonRedisSerializer;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)
//public class RedisCacheAutoConfiguration {
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        // key采用String的序列化方式
//        template.setKeySerializer(stringRedisSerializer);
//        // hash的key也采用String的序列化方式
//        template.setHashKeySerializer(stringRedisSerializer);
//
//        // value序列化方式采用fastJson
//        template.setValueSerializer(fastJsonRedisSerializer);
//        // hash的value序列化方式采用fastJson
//        template.setHashValueSerializer(fastJsonRedisSerializer);
//
//        template.afterPropertiesSet();
//        return template;
//    }
//}
