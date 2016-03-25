package com.fabledlands4android.elements;

import com.facebook.react.bridge.WritableNativeMap;

import java.util.ArrayList;

public class OutcomesElement extends Element {
    private OutcomeElement success;
    private OutcomeElement failure;

    @Override
    public String getElementType() {
        return "outcomes";
    }

    public OutcomesElement(ArrayList<Element> elements) {

    }

    @Override
    public WritableNativeMap toElement() {
        return null;
    }

    public OutcomeElement getSuccess() {
        return success;
    }

    public OutcomeElement getFailure() {
        return failure;
    }
}
