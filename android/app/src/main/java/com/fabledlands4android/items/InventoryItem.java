package com.fabledlands4android.items;

import com.facebook.react.bridge.WritableNativeMap;

public abstract class InventoryItem {
    String name;

    InventoryItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public WritableNativeMap toWritable() {
        WritableNativeMap m = new WritableNativeMap();
        m.putString("name", this.getName());
        return m;
    }
}
