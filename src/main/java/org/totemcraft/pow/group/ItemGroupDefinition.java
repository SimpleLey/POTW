package org.totemcraft.pow.group;

import lombok.Data;
import net.minecraft.resources.ResourceLocation;
import org.totemcraft.pow.localization.LocalizationStrings;

@Data
public class ItemGroupDefinition {
    private ResourceLocation id;
    private LocalizationStrings displayName;
}
