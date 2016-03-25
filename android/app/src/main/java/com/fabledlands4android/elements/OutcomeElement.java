package com.fabledlands4android.elements;

import com.facebook.react.bridge.WritableNativeMap;

public class OutcomeElement extends Element {
    Integer section;
    boolean success;

    @Override
    public String getElementType() {
        return null;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Integer getSection() {
        return section;
    }

    public OutcomeElement(boolean isSuccess, Integer section) {
        this.success = isSuccess;
        this.section = section;
    }

    @Override
    public WritableNativeMap toElement() {
        return null;
    }
}
