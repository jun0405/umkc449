package edu.umkc.allinone;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * Translate API JUnit class
 *
 * @author Jun Yang
 */
public class TranslateAPIUnitTest {
    private TranslateAPI api;

    @Before
    public void setup() {
        api = new TranslateAPI();
    }

    @Test
    public void valid_input() throws IOException {
        JSONObject fetch = api.fetch("Hello", "English", "Chinese");
        Assert.assertEquals(api.getTranslatedText(fetch), "你好");
    }

    @Test
    public void invalid_input() throws IOException {
        JSONObject fetch = api.fetch("Hello", "test", "test2");
        Assert.assertEquals(fetch.get("code"), new Long(501));
    }
}