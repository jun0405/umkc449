package edu.umkc.allinone;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Currency API JUnit class
 *
 * @author Jun Yang
 */
public class CurrencyAPIUnitTest {
    private CurrencyAPI api;

    @Before
    public void setup() {
        api = new CurrencyAPI();
    }

    @Test
    public void valid_input() throws IOException {
        JSONObject fetch = api.fetch("USD", "EUR");
        Assert.assertNotNull(api.getCurrencyRate(fetch));
    }

    @Test(expected = NoSuchElementException.class)
    public void invalid_input() throws IOException {
        JSONObject fetch = api.fetch("test", "test2");
        api.getCurrencyRate(fetch);
    }
}