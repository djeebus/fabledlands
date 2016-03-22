package com.fabledlands4android.parsers;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public abstract interface AbstractFileParser {
    Object parse(XmlPullParser parser)
            throws XmlPullParserException, IOException;
}
