package com.fabledlands4android.elements;

import com.facebook.react.bridge.WritableNativeMap;

import java.util.ArrayList;

public class ParagraphElement extends Element {
    ArrayList<Element> content;

    public ParagraphElement(ArrayList<Element> content) {
        this.content = content;
    }

    @Override
    public String getElementType() {
        return "paragraph";
    }

    @Override
    public WritableNativeMap toElement() {
        return null;
    }
}
