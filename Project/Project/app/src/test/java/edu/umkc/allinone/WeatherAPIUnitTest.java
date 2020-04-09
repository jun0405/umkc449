package edu.umkc.allinone;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * Weather API JUnit class
 *
 * @author Jun Yang
 */
public class WeatherAPIUnitTest {
    private WeatherAPI api;

    @Before
    public void setup() {
        api = new WeatherAPI(66062);
    }

    @Test
    public void valid_input() throws IOException {
        JSONObject jsonObj = api.fetch();
        Assert.assertEquals(api.getName(jsonObj), "Olathe");
        Assert.assertNotNull(api.getLon(jsonObj));
        Assert.assertNotNull(api.getLat(jsonObj));
        Assert.assertNotNull(api.getSummary(jsonObj));
        Assert.assertNotNull(api.getDescription(jsonObj));
        Assert.assertNotNull(api.getTemp(jsonObj));
        Assert.assertNotNull(api.getTempMin(jsonObj));
        Assert.assertNotNull(api.getTempMax(jsonObj));
        Assert.assertNotNull(api.getFeelsLike(jsonObj));
        Assert.assertNotNull(api.getPressure(jsonObj));
        Assert.assertNotNull(api.getHumidity(jsonObj));
        Assert.assertNotNull(api.getWindSpeed(jsonObj));
        Assert.assertNotNull(api.getWindDirection(jsonObj));
        Assert.assertNotNull(api.getSunrise(jsonObj));
        Assert.assertNotNull(api.getSunset(jsonObj));
    }

    @Test(expected = NullPointerException.class)
    public void invalid_input() throws IOException {
        api = new WeatherAPI(123456789);
        JSONObject jsonObj = api.fetch();
        api.getName(jsonObj);
        api.getLon(jsonObj);
        api.getLat(jsonObj);
        api.getSummary(jsonObj);
        api.getDescription(jsonObj);
        api.getTemp(jsonObj);
        api.getTempMin(jsonObj);
        api.getTempMax(jsonObj);
        api.getFeelsLike(jsonObj);
        api.getPressure(jsonObj);
        api.getHumidity(jsonObj);
        api.getWindSpeed(jsonObj);
        api.getWindDirection(jsonObj);
        api.getSunrise(jsonObj);
        api.getSunset(jsonObj);
    }
}