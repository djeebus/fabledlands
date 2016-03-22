package com.fabledlands4android.items;

import org.xmlpull.v1.XmlPullParser;

public class GenericItem extends InventoryItem {
    String name;

    public GenericItem(String name) {
        this.name = name;
    }

    public static GenericItem parse(XmlPullParser parser) {
        String name = parser.getAttributeValue(null, "name");
        return new GenericItem(name);
    }
}
