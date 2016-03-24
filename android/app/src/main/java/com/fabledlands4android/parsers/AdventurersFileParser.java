package com.fabledlands4android.parsers;

import android.util.Log;

import com.fabledlands4android.*;
import com.fabledlands4android.items.InventoryItem;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdventurersFileParser implements FileParser {
    int getAmount(XmlPullParser parser) {
        String value = parser.getAttributeValue(null, "amount");
        return Integer.parseInt(value);
    }

    HashMap<String, Profession> parseProfessions(XmlPullParser parser, HashMap<String, Profession> professions)
            throws XmlPullParserException, IOException {
        AbilityType[] abilityOrder = null;

        while (true) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    switch (parser.getName()) {
                        case "header":
                            String headers = parser.nextText();
                            String[] parts = headers.split(" ");
                            abilityOrder = new AbilityType[parts.length];
                            for (int x = 0; x < parts.length; x++) {
                                abilityOrder[x] = AbilityType.valueOf(parts[x]);
                            }
                            break;

                        case "profession":
                            String professionName = parser.getAttributeValue(null, "name");
                            String abilityText = parser.nextText();
                            String[] abilityParts = abilityText.split(" ");

                            HashMap<AbilityType, Integer> currentAbilities = new HashMap<>(abilityParts.length);
                            for (int x = 0; x < abilityParts.length; x++) {
                                currentAbilities.put(
                                        abilityOrder[x],
                                        Integer.parseInt(abilityParts[x])
                                );
                            }

                            professions.put(
                                    professionName,
                                    new Profession(
                                            professionName,
                                            currentAbilities
                                    )
                            );
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (parser.getName()) {
                        case "abilities":
                            return professions;
                    }

                    break;
            }
        }
    }

    void parseItems(XmlPullParser parser, HashMap<String, Profession> professions)
            throws IOException, XmlPullParserException
    {
        while (true) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    String professionName = parser.getAttributeValue(null, "profession");
                    InventoryItem item = ItemParser.parse(parser);

                    if (professionName == null) {
                        for (Profession profession : professions.values()) {
                            profession.addInitialItem(item);
                        }
                    } else {
                        Profession profession = professions.get(professionName);
                        profession.addInitialItem(item);
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("items"))
                        return;
                    Log.d("TESTING", "tag: " + parser.getName());
                    break;
            }
        }
    }

    ArrayList<Adventurer> parsePrebuiltAdventurers(
            XmlPullParser parser, HashMap<String, Profession> professions,
            int initialStamina, int initialRank, int initialGold
    )
            throws IOException, XmlPullParserException
    {
        ArrayList<Adventurer> adventurers = new ArrayList<>();

        while (true) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    switch (parser.getName()) {
                        case "adventurer":
                            String name = parser.getAttributeValue(null, "name");
                            String professionName = parser.getAttributeValue(null, "profession");
                            String genderName = parser.getAttributeValue(null, "gender");

                            Profession profession = professions.get(professionName);
                            if (profession == null) {
                                throw new IOException("No profession: " + professionName);
                            }

                            adventurers.add(
                                    new Adventurer(
                                            name, initialRank, initialStamina,
                                            initialGold, profession, genderName.equalsIgnoreCase("m")
                                    )
                            );
                            break;
                    }

                case XmlPullParser.END_TAG:
                    switch (parser.getName()) {
                        case "adventurer":
                            return adventurers;
                    }
            }
        }
    }

    @Override
    public Object parse(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        ArrayList<Adventurer> prebuiltAdventurers = new ArrayList<>();
        HashMap<String, Profession> professions = new HashMap<>();
        int initialStamina = 0,
            initialRank = 0,
            initialGold = 0;
        ArrayList<InventoryItem> items = new ArrayList<>();

        int eventType;
        while ((eventType = parser.getEventType()) != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    switch (parser.getName()) {
                        case "abilities":
                            this.parseProfessions(parser, professions);
                            break;

                        case "items":
                            this.parseItems(parser, professions);
                            break;

                        case "starting":
                            prebuiltAdventurers = this.parsePrebuiltAdventurers(
                                    parser, professions,
                                    initialStamina, initialRank, initialGold
                            );
                            break;

                        case "stamina":
                            initialStamina = this.getAmount(parser);
                            break;

                        case "rank":
                            initialRank = this.getAmount(parser);
                            break;

                        case "gold":
                            initialGold = this.getAmount(parser);
                            break;
                    }
                    break;
            }

            parser.next();
        }

        return new AdventurersFile(
                professions,
                initialStamina,
                initialRank,
                initialGold,
                items,
                prebuiltAdventurers
        );
    }
}