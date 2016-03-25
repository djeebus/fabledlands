package com.fabledlands4android.elements;

import com.fabledlands4android.AbilityType;
import com.facebook.react.bridge.WritableNativeMap;

public class AbilityRollElement extends Element {
    AbilityType abilityType;
    Integer level;

    @Override
    public String getElementType() {
        return "abilityRoll";
    }

    public AbilityType getAbilityType() {
        return this.abilityType;
    }

    public Integer getLevel() {
        return this.level;
    }

    public AbilityRollElement(AbilityType abilityType, Integer level) {
        this.abilityType = abilityType;
        this.level = level;
    }

    @Override
    public WritableNativeMap toElement() {
        return null;
    }
}
