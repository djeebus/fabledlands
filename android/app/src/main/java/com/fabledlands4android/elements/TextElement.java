package com.fabledlands4android.elements;

import com.facebook.react.bridge.WritableNativeMap;

public class TextElement extends Element {
    String text;

    @Override
    public String getElementType() {
        return "text";
    }

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public WritableNativeMap toElement() {
        WritableNativeMap map = new WritableNativeMap();
        map.putString("text", this.text);
        return map;
    }

    public String getText() {
        return text;
    }
}
