package com.fabledlands4android;

import com.fabledlands4android.elements.Element;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.ArrayList;

public class BookPage {
    Integer bookId, pageId;
    ArrayList<Element> elements;

    public Integer getBookId() {
        return this.bookId;
    }

    public Integer getPageId() {
        return this.pageId;
    }

    public ArrayList<Element> getElements() {
        return this.elements;
    }

    public BookPage(int bookId, int pageId, ArrayList<Element> elements) {
        this.bookId = bookId;
        this.pageId = pageId;
        this.elements = elements;
    }

    public WritableNativeMap toWritable() {
        return null;
    }
}
