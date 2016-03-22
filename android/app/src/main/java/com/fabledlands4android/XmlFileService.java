package com.fabledlands4android;

import android.content.res.AssetManager;
import com.fabledlands4android.parsers.AbstractFileParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

class XmlFileService {
    private HashMap<String, Object> _cache = new HashMap<>();

    private XmlPullParserFactory factory;
    private AssetManager assets;

    public XmlFileService(AssetManager assets) throws XmlPullParserException {
        this.factory = XmlPullParserFactory.newInstance();
        this.assets = assets;
    }

    public <T> T get(
            String filename,
            AbstractFileParser fileParser
    ) {
        if (this._cache.containsKey(filename)) {
            return (T)this._cache.get(filename);
        }

        InputStream inputStream = null;
        try {
            inputStream = this.assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        XmlPullParser parser = null;
        try {
            parser = this.factory.newPullParser();
            parser.setInput(inputStream, null);
            return (T)fileParser.parse(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}