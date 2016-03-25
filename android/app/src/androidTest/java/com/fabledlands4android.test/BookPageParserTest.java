package com.fabledlands4android.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;

import com.fabledlands4android.AbilityType;
import com.fabledlands4android.Book;
import com.fabledlands4android.BookPage;
import com.fabledlands4android.XmlFileService;
import com.fabledlands4android.elements.AbilityRollElement;
import com.fabledlands4android.elements.OutcomesElement;
import com.fabledlands4android.elements.TextElement;
import com.facebook.soloader.SoLoader;

public class BookPageParserTest extends InstrumentationTestCase {
    BookPage bookPage = null;
    final int testBookId = 1;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        Instrumentation instrumentation = this.getInstrumentation();
        assertNotNull(instrumentation);

        Context appContext = instrumentation.getTargetContext();
        assertNotNull(appContext);

        SoLoader.init(appContext, false);

        AssetManager assets = appContext.getAssets();
        assertNotNull(assets);

        XmlFileService service = new XmlFileService(assets);

        Book book = new Book(this.testBookId, service);
        this.bookPage = book.getStoryPage(79);
    }

    public void testDifficultyRoll() {
        AbilityRollElement element =
                (AbilityRollElement)this.bookPage.getElements().get(0);
        assertEquals(AbilityType.SCOUTING, element.getAbilityType());
        assertEquals(14, (int) element.getLevel());
    }

    public void testText() {
        TextElement element =
                (TextElement)this.bookPage.getElements().get(1);
        assertEquals(
                ", as you try to lose them in the tunnels. It is hard because " +
                "they know the layout better than you do.", element.getText());
    }

    public void testOutcomes() {
        OutcomesElement element =
                (OutcomesElement)this.bookPage.getElements().get(2);
        assertEquals(224, (int)element.getSuccess().getSection());
        assertEquals(381, (int)element.getFailure().getSection());
    }
}
