package com.fabledlands4android;

import com.fabledlands4android.parsers.AdventurersFileParser;

import java.util.Formatter;

public class Book {
    private Formatter formatter = new Formatter();
    private Integer bookId;
    private XmlFileService xmlFileService;

    public Book(Integer bookId, XmlFileService xmlFileService) {
        this.bookId = bookId;
        this.xmlFileService = xmlFileService;
    }

    public AdventurersFile getAdventurersInfo() {
        String filename = this.formatter.format(
                "book%s/Adventurers.xml",
                this.bookId.toString()).toString();

        return this.xmlFileService.get(
                filename,
                new AdventurersFileParser()
        );
    }
}
