package com.fabledlands4android.elements;

import com.facebook.react.bridge.WritableNativeMap;

public abstract class Element {
    public abstract String getElementType();
    public abstract WritableNativeMap toElement();
}
