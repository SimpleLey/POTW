package org.totemcraft.pow.localization;

import lombok.Getter;

import java.util.Map;

public class LocalizationStrings {
    @Getter
    private Map<String, String[]> values;
    private String lastLanguage;
    private String[] currentValue;

    public LocalizationStrings(Map<String, String[]> values) {
        this.values = values;
    }

    public String[] getCurrentStrings() {
        if (LocalizationManager.INSTANCE.getCurrentCode().equals(lastLanguage)) return currentValue;
        lastLanguage = LocalizationManager.INSTANCE.getCurrentCode();

        currentValue = values.get(LocalizationManager.INSTANCE.getCurrentCode());
        if (currentValue != null) return currentValue;

        for (String[] value : values.values()) {
            currentValue = value;
            return value;
        }

        return null;
    }

    public String getSingleString() {
        var current = getCurrentStrings();
        if (current != null && current.length > 0) return current[0];
        return null;
    }
}
