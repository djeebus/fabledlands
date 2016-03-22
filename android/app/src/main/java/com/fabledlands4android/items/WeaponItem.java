package com.fabledlands4android.items;

import org.xmlpull.v1.XmlPullParser;

public class WeaponItem extends InventoryItem {
    String name, professionName;

    public WeaponItem(String name, String professionName) {
        this.name = name;
        this.professionName = professionName;
    }

    public static WeaponItem parse(XmlPullParser parser) {
        String name = parser.getAttributeValue(null, "name");
        String professionName = parser.getAttributeValue(null, "profession");

        return new WeaponItem(name, professionName);
    }
}
