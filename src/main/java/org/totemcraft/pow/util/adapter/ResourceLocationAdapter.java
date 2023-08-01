package org.totemcraft.pow.util.adapter;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import org.totemcraft.pow.R;

import java.lang.reflect.Type;

public class ResourceLocationAdapter implements JsonSerializer<ResourceLocation>, JsonDeserializer<ResourceLocation> {
    @Override
    public ResourceLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return R.location(json.getAsString());
    }

    @Override
    public JsonElement serialize(ResourceLocation src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}
