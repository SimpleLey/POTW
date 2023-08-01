package org.totemcraft.pow.util.adapter;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;

public class CaseInsensitiveEnumTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<T> rawType = (Class<T>) typeToken.getRawType();
        if (!rawType.isEnum()) {
            return null;
        }

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, typeToken);
        final Enum<?>[] constants = (Enum<?>[]) rawType.getEnumConstants();

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                String value = in.nextString();
                for (Enum<?> constant : constants) {
                    if (constant.name().equalsIgnoreCase(value)) {
                        // 返回匹配的枚举常量
                        return (T) constant;
                    }
                }
                throw new IllegalArgumentException("Unknown enum value: " + value);
            }
        };
    }
}