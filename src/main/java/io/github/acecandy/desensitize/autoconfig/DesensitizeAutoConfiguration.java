package io.github.acecandy.desensitize.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.acecandy.desensitize.impl.DesensitizeSerializer;

/**
 * 脱敏自动配置
 *
 * @author tangningzhu
 * @date 2021/9/26
 */
@Configuration
public class DesensitizeAutoConfiguration {

    @Bean
    public DesensitizeSerializer sensitiveSerializer() {
        return new DesensitizeSerializer();
    }

}