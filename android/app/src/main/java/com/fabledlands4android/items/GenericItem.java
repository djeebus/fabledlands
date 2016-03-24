package com.fabledlands4android.items;

import org.xmlpull.v1.XmlPullParser;

public class GenericItem extends InventoryItem {
    public GenericItem(String name) {
        super(name);
    }

    public static GenericItem parse(XmlPullParser parser) {
        String name = parser.getAttributeValue(null, "name");
        return new GenericItem(name);
    }
}
