package com.fabledlands4android.items;

import org.xmlpull.v1.XmlPullParser;

public class WeaponItem extends InventoryItem {
    public WeaponItem(String name) {
        super(name);
    }

    public static WeaponItem parse(XmlPullParser parser) {
        String name = parser.getAttributeValue(null, "name");

        return new WeaponItem(name);
    }
}
