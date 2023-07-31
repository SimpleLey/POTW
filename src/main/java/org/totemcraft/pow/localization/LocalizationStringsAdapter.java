package org.totemcraft.pow.localization;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LocalizationStringsAdapter implements JsonSerializer<LocalizationStrings>, JsonDeserializer<LocalizationStrings> {
    @Override
    public LocalizationStrings deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonArray()) {
            return new LocalizationStrings(Map.of("en_us", context.deserialize(json, String[].class)));
        } else if (json.isJsonPrimitive()) {
            return new LocalizationStrings(Map.of("en_us", new String[]{json.getAsString()}));
        } else if (json.isJsonObject()) {
            Map<String, String[]> result = new HashMap<>();

            json.getAsJsonObject().entrySet().forEach(entry -> {
                if (entry.getValue().isJsonArray()) {
                    result.put(entry.getKey(), context.deserialize(entry.getValue(), String[].class));
                } else if (entry.getValue().isJsonPrimitive()) {
                    result.put(entry.getKey(), new String[]{ entry.getValue().getAsString() });
                }
            });

            return new LocalizationStrings(result);
        }

        return null;
    }

    @Override
    public JsonElement serialize(LocalizationStrings src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getValues());
    }
}
