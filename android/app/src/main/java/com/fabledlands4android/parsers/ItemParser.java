package com.fabledlands4android.parsers;

import com.fabledlands4android.items.ArmourItem;
import com.fabledlands4android.items.GenericItem;
import com.fabledlands4android.items.InventoryItem;
import com.fabledlands4android.items.WeaponItem;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;

public class ItemParser {
    static InventoryItem parse(XmlPullParser parser) throws IOException {
        switch (parser.getName()) {
            case "armour": return ArmourItem.parse(parser);
            case "weapon": return WeaponItem.parse(parser);
            case "item": return GenericItem.parse(parser);
            default:
                throw new IOException("Unknown item: " + parser.getName());
        }
    }

}
