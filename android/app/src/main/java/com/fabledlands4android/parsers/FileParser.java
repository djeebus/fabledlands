package com.fabledlands4android.parsers;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public interface FileParser {
    Object parse(XmlPullParser parser)
            throws XmlPullParserException, IOException;
}
