package com.wxss.springbootrediscluster.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;


@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisClusterProperties redisClusterProperties){

        RedisClusterConfiguration rcc = new RedisClusterConfiguration();
        List<String> nodesList=redisClusterProperties.getNodes();
        String host=null;
        int port=0;
        if (nodesList != null && nodesList.size()>0){
            for(String node:nodesList) {
                String[] hostAndPortArr = node.split(":");
                host= hostAndPortArr[0];
                port=Integer.parseInt(hostAndPortArr[1]);
                rcc.addClusterNode(new RedisNode(host,port));
            }
        }else {

        }

        return new JedisConnectionFactory(rcc);
    }

    /**
     *  配置RedisTemplate
     * @param factory  spring 会将其传递过来
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("jedisConnectionFactory") RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);
        /**
         * 默认的序列化（JdkSerializationRedisSerializer）会给所有的key,value的原始字符前，都加了一串字符（例如：\xAC\xED\x00\），不具备可读性
         * 所以修改为Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
         */
        Jackson2JsonRedisSerializer jacksonSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerializer.setObjectMapper(om);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 值采用json序列化
        template.setValueSerializer(jacksonSerializer);

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSerializer);
        template.afterPropertiesSet();

        return template;
    }
}
