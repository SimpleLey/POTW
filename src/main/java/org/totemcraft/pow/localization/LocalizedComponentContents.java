package org.totemcraft.pow.localization;

import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;

import java.util.Optional;

public class LocalizedComponentContents implements ComponentContents {
    private LocalizationStrings value;

    public LocalizedComponentContents(LocalizationStrings value) {
        this.value = value;
    }

    @Override
    public <T> Optional<T> visit(FormattedText.ContentConsumer<T> consumer) {
        return consumer.accept(toString());
    }

    @Override
    public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> consumer, Style style) {
        return consumer.accept(style, toString());
    }

    @Override
    public String toString() {
        return String.join("\n", value.getCurrentStrings());
    }
}
