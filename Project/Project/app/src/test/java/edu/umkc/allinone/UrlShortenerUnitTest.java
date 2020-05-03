package edu.umkc.allinone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 *  Url shortener API JUnit class
 *
 * @author Jun Yang
 */
public class UrlShortenerUnitTest {
    private UrlShortenerAPI api;

    @Before
    public void setup() {
        api = new UrlShortenerAPI();
    }

    @Test
    public void valid_input() throws IOException {
        String newUrl = api.fetch("https://github.com/jun0405/umkc449/wiki/Project-Increment4");
        Assert.assertNotNull(newUrl);
    }

    @Test(expected = NullPointerException.class)
    public void invalid_input() throws IOException {
        api.fetch("test");
    }
}