package com.fabledlands4android;

import android.content.Context;
import android.content.res.AssetManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import org.xmlpull.v1.XmlPullParserException;

public class BookModule extends ReactContextBaseJavaModule {
    public BookModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "BookModule";
    }

    @ReactMethod
    public void getBook(Integer bookId, Promise promise)
            throws XmlPullParserException
    {
        ReactApplicationContext reactContext = this.getReactApplicationContext();
        Context appContext = reactContext.getApplicationContext();
        AssetManager assets = appContext.getAssets();

        XmlFileService service = new XmlFileService(assets);
        promise.resolve(new Book(bookId, service));
    }
}