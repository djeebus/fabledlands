package com.fabledlands4android;

import com.fabledlands4android.parsers.AdventurersFileParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Formatter;

public class Book {
    private Formatter formatter = new Formatter();
    private Integer bookId;
    private XmlFileService xmlFileService;

    public Book(Integer bookId, XmlFileService xmlFileService) {
        this.bookId = bookId;
        this.xmlFileService = xmlFileService;
    }

    public AdventurersFile getAdventurersInfo() throws IOException, XmlPullParserException {
        String filename = this.formatter.format(
                "book%s/Adventurers.xml",
                this.bookId.toString()).toString();

        return this.xmlFileService.get(
                filename,
                new AdventurersFileParser()
        );
    }
}
