package com.fabledlands4android.items;

import org.xmlpull.v1.XmlPullParser;

public class ArmourItem extends InventoryItem {
    private Integer bonus;

    ArmourItem(String name, Integer bonus) {
        super(name);
        this.bonus = bonus;
    }

    public static ArmourItem parse(XmlPullParser parser) {
        String name = parser.getAttributeValue(null, "name");
        String bonus = parser.getAttributeValue(null, "bonus");

        return new ArmourItem(name, Integer.parseInt(bonus));
    }

    public Integer getBonus() {
        return bonus;
    }
}
