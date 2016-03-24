package com.fabledlands4android;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class BookModule extends ReactContextBaseJavaModule {
    private static final String TAG = "BookModule";

    private XmlFileService service = null;

    public BookModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    private XmlFileService getXmlFileService() throws XmlPullParserException {
        if (this.service == null) {
            Log.i(TAG, "creating xml file service");
            ReactApplicationContext reactContext = this.getReactApplicationContext();
            Context appContext = reactContext.getApplicationContext();
            AssetManager assets = appContext.getAssets();

            this.service = new XmlFileService(assets);
            Log.i(TAG, "xml file service created");
        }

        return this.service;
    }

    @Override
    public String getName() {
        return "BookModule";
    }

    @ReactMethod
    public void getCharacterCreationInfo(Integer bookId, Promise promise)
            throws XmlPullParserException, IOException {
        Book book = new Book(bookId, this.getXmlFileService());
        AdventurersFile file = book.getAdventurersInfo();
        promise.resolve(file.toWritable());
    }
}