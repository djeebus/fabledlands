package com.fabledlands4android.test.suite;

import com.fabledlands4android.test.AdventurersFileParserTest;
import com.fabledlands4android.test.BookPageParserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdventurersFileParserTest.class,
        BookPageParserTest.class,
})
public class UnitTestSuite {}
