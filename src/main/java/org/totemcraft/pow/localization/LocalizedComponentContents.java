package org.totemcraft.pow.localization;

import net.minecraft.network.chat.ComponentContents;

public class LocalizedComponentContents implements ComponentContents {
    private LocalizationStrings value;

    public LocalizedComponentContents(LocalizationStrings value) {
        this.value = value;
    }
}
