package com.fabledlands4android;

import android.content.res.AssetManager;
import android.util.Log;
import com.fabledlands4android.parsers.FileParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class XmlFileService {
    private static String TAG = "XmlFileService";

    private HashMap<String, Object> _cache = new HashMap<>();

    private XmlPullParserFactory factory;
    private AssetManager assets;

    public XmlFileService(AssetManager assets) throws XmlPullParserException {
        this.factory = XmlPullParserFactory.newInstance();
        this.assets = assets;
    }

    public <T> T get(
            String filename,
            FileParser fileParser
    ) throws XmlPullParserException, IOException {
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

        XmlPullParser parser = this.factory.newPullParser();
        parser.setInput(inputStream, null);

        Object result = fileParser.parse(parser);
        return (T)result;
    }
}