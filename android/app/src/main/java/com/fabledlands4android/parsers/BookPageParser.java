package com.fabledlands4android.parsers;

import com.fabledlands4android.AbilityType;
import com.fabledlands4android.BookPage;
import com.fabledlands4android.elements.AbilityRollElement;
import com.fabledlands4android.elements.Element;
import com.fabledlands4android.elements.OutcomeElement;
import com.fabledlands4android.elements.OutcomesElement;
import com.fabledlands4android.elements.ParagraphElement;
import com.fabledlands4android.elements.TextElement;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookPageParser implements FileParser {
    Integer bookId, pageId;

    public BookPageParser(Integer bookId, Integer pageId) {
        this.bookId = bookId;
        this.pageId = pageId;
    }

    Element parseElement(XmlPullParser parser) throws IOException, XmlPullParserException {
        String elementName = parser.getName();
        switch (elementName) {
            case "p":
                return new ParagraphElement(this.parseElements(parser));

            case "difficulty":
                String level = parser.getAttributeValue(null, "level");
                String abilityType = parser.getAttributeValue(null, "ability");

                return new AbilityRollElement(
                        AbilityType.valueOf(abilityType.toUpperCase()),
                        Integer.parseInt(level)
                );

            case "outcomes":
                ArrayList<Element> elements = this.parseElements(parser);
                return new OutcomesElement(elements);

            case "success":
            case "failure":
                Boolean isSuccess = elementName.equals("success");
                String section = parser.getAttributeValue(null, "section");
                return new OutcomeElement(isSuccess, Integer.parseInt(section));

            default:
                throw new IOException("Unknown tag: " + parser.getName());
        }
    }

    public ArrayList<Element> parseElements(XmlPullParser parser)
            throws IOException, XmlPullParserException
    {
        int count = 0;
        ArrayList<Element> elements = new ArrayList<>();
        while (true) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    count ++;
                    elements.add(this.parseElement(parser));
                    break;

                case XmlPullParser.TEXT:
                    String text = parser.getText().trim();
                    if (!text.isEmpty()) {
                        elements.add(new TextElement(parser.getText()));
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if (count == 0) {
                        return elements;
                    }

                    count --;
                    break;

                default:
                    throw new IOException("In a weird place! type=" + parser.getEventType());
            }
        }
    }

    @Override
    public Object parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        while (true) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    ArrayList<Element> elements = this.parseElements(parser);
                    return new BookPage(this.bookId, this.pageId, elements);

                default:
                    throw new IOException("Unknown place!");
            }
        }
    }
}
