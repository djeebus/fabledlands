package com.fabledlands4android.androidTest;

import com.fabledlands4android.AdventurersFile;
import com.fabledlands4android.parsers.AdventurersFileParser;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import static org.junit.Assert.*;

public class AdventurersFileParserTest {
    @Test
    public void testBook1ParseAdventurersFile() {
        XmlPullParserFactory factory;
        try {
            factory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            fail(e.toString());
            return;
        }

        XmlPullParser xmlPullParser;
        try {
            xmlPullParser = factory.newPullParser();
        } catch (XmlPullParserException e) {
            fail(e.toString());
            return;
        }

        AdventurersFileParser parser = new AdventurersFileParser();
        AdventurersFile file;
        try {
            file = (AdventurersFile)parser.parse(xmlPullParser);
        } catch (Exception e) {
            fail(e.toString());
            return;
        }

        assertNotNull(file);
    }
}