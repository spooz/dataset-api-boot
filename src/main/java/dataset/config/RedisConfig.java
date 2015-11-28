package dataset.config;

import dataset.domain.DataSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.awt.print.Book;
import javax.inject.*;

/**
 * Created by programowanie on 28.11.2015.
 */
@Configuration
public class RedisConfig {

    @Inject
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public JacksonJsonRedisSerializer<DataSet> jacksonJsonRedisJsonSerializer() {
        JacksonJsonRedisSerializer<DataSet> jacksonJsonRedisJsonSerializer = new JacksonJsonRedisSerializer<>(DataSet.class);
        return jacksonJsonRedisJsonSerializer;
    }

    @Bean
    public RedisTemplate<String, DataSet> redisTemplate() {
        RedisTemplate<String, DataSet> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setHashKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
        redisTemplate.setHashValueSerializer(jacksonJsonRedisJsonSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisAtomicLong redisAtomicLong() {
        final RedisAtomicLong redisAtomicLong = new RedisAtomicLong("dataset", jedisConnectionFactory);
        return redisAtomicLong;
    }
}
