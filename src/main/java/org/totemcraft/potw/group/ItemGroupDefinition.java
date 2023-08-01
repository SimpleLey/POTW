package org.totemcraft.potw.group;

import lombok.Data;
import net.minecraft.resources.ResourceLocation;
import org.totemcraft.potw.localization.LocalizationStrings;

@Data
public class ItemGroupDefinition {
    private ResourceLocation id;
    private LocalizationStrings displayName;
    private ResourceLocation icon;
}
