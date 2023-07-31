package org.totemcraft.pow.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import org.totemcraft.pow.localization.LocalizationStrings;
import org.totemcraft.pow.localization.LocalizationStringsAdapter;
import org.totemcraft.pow.util.adapter.CaseInsensitiveEnumTypeAdapterFactory;
import org.totemcraft.pow.util.adapter.ResourceLocationAdapter;

import java.lang.reflect.Type;

public interface Json {
    Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .setLenient()
            .registerTypeAdapter(ResourceLocation.class, new ResourceLocationAdapter())
            .registerTypeAdapter(LocalizationStrings.class, new LocalizationStringsAdapter())
            .registerTypeAdapterFactory(new CaseInsensitiveEnumTypeAdapterFactory())
            .create();

    static <T> T from(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    static <T> T from(String json, Class<T> type) {
        return GSON.fromJson(json, type);
    }

    static <T> T from(JsonElement json, Type type) {
        return GSON.fromJson(json, type);
    }

    static <T> T from(JsonElement json, Class<T> type) {
        return GSON.fromJson(json, type);
    }
    static String to(Object any) {
        return GSON.toJson(any);
    }

    static JsonElement toTree(Object any) {
        return GSON.toJsonTree(any);
    }
}
