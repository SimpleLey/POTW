package org.totemcraft.pow.localization;

import net.minecraft.client.Minecraft;

public enum LocalizationManager {
    INSTANCE;

    public String getCurrentCode() {
        return Minecraft.getInstance().getLanguageManager().getSelected();
    }
}
