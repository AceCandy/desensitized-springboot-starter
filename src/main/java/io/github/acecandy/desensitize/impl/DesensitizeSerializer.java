package io.github.acecandy.desensitize.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import io.github.acecandy.desensitize.autoconfig.Desensitize;

import cn.hutool.core.util.StrUtil;

/**
 * 脱敏序列化器
 *
 * @author tangningzhu
 * @date 2021/9/26
 */
public class DesensitizeSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private Desensitize sensitive;

    public DesensitizeSerializer(Desensitize sensitive) {
        this.sensitive = sensitive;
    }

    public DesensitizeSerializer() {
    }

    @Override
    public void serialize(Object o, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (!(o instanceof Number || o instanceof String)) {
            gen.writeObject(o);
            return;
        }
        String value = String.valueOf(o);
        if (sensitive == null || StrUtil.isBlank(value)) {
            gen.writeObject(o);
            return;
        }
        DesensitizeEnum type = sensitive.value();
        if (DesensitizeEnum.CUSTOM.equals(type)) {
            value = DesensitizeUtil.custom(value, sensitive.start(), sensitive.end());
        } else {
            value = DesensitizeUtil.desensitized(value, type);
        }
        if (StrUtil.isBlank(value)) {
            gen.writeObject(o);
        } else {
            gen.writeString(value);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        Desensitize annotation = property.getAnnotation(Desensitize.class);

        if (annotation != null) {
            return new DesensitizeSerializer(annotation);
        }
        return this;
    }

}