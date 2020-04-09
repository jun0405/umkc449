package edu.umkc.allinone;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * Country API JUnit class
 *
 * @author Jun Yang
 */
public class CountryAPIUnitTest {
    private CountryAPI api;

    @Before
    public void setup() {
        api = new CountryAPI();
    }

    @Test
    public void valid_input() throws IOException {
        JSONObject jsonObj = api.fetch("USA");
        Assert.assertEquals(api.getCountryName(jsonObj), "United States of America");
        Assert.assertEquals(api.getCapital(jsonObj), "Washington, D.C.");
        Assert.assertEquals(api.getRegion(jsonObj), "Americas");
        Assert.assertEquals(api.getSubregion(jsonObj), "Northern America");
        Assert.assertEquals(api.getDemonym(jsonObj), "American");
        Assert.assertEquals(api.getArea(jsonObj), new Double(9629091.0));
    }

    @Test(expected = NullPointerException.class)
    public void invalid_input() throws IOException {
        JSONObject jsonObj = api.fetch("test");
        api.getCountryName(jsonObj);
        api.getCapital(jsonObj);
        api.getRegion(jsonObj);
        api.getSubregion(jsonObj);
        api.getDemonym(jsonObj);
        api.getArea(jsonObj);
    }
}