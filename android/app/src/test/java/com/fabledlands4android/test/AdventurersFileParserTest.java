package com.fabledlands4android.androidTest;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.fabledlands4android.AdventurersFile;
import com.fabledlands4android.parsers.AdventurersFileParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class AdventurersFileParserTest {
    XmlPullParser xmlPullParser = null;

    @Before
    public void createXmlPullParser() throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        this.xmlPullParser = factory.newPullParser();
    }

    @Test
    public void testBook1ParseAdventurersFile() throws IOException, XmlPullParserException {
        AdventurersFileParser parser = new AdventurersFileParser();
        AdventurersFile file = (AdventurersFile)parser.parse(this.xmlPullParser);

        assertNotNull(file);
    }
}