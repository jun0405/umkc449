package edu.umkc.allinone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Weather API class
 *
 * @author Jun Yang
 */

public class WeatherAPI {
    private String endpoint;

    /**
     * Constructor
     *
     * @param zipCode zip code for the search address
     */
    public WeatherAPI(int zipCode) {
        this.endpoint = "https://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&units=imperial&APPID=907a0d541e90e08a4700053800659309";
    }

    /**
     * Fetch result from openweathermap.org via REST API
     *
     * @return JSON object for the fetched weather info
     */
    public JSONObject fetch() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(this.endpoint).method("GET", null).build();
        Response response = client.newCall(request).execute();
        Object obj = JSONValue.parse(response.body().string());
        return (JSONObject)obj;
    }

    /**
     * Get the name for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the name of the city
     */
    public String getName(JSONObject jsonObj){
        Object name = jsonObj.get("name");
        return name.toString();
    }

    /**
     * Get the longitude for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the longitude of the city
     */
    public double getLon(JSONObject jsonObj){
        Object coord = jsonObj.get("coord");
        JSONObject obj = (JSONObject) JSONValue.parse(coord.toString());
        return (double)obj.get("lon");
    }

    /**
     * Get the latitude for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the latitude of the city
     */
    public double getLat(JSONObject jsonObj){
        Object coord = jsonObj.get("coord");
        JSONObject obj = (JSONObject) JSONValue.parse(coord.toString());
        return (double) obj.get("lat");
    }

    /**
     * Get the summary of the weather for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the summary of the weather
     */
    public String getSummary(JSONObject jsonObj){
        Object weather = jsonObj.get("weather");
        JSONArray jsonArr = (JSONArray) JSONValue.parse(weather.toString());
        JSONObject obj = (JSONObject) JSONValue.parse(jsonArr.get(0).toString());
        return obj.get("main").toString();
    }

    /**
     * Get the description of the weather for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the description of the weather
     */
    public String getDescription(JSONObject jsonObj){
        Object weather = jsonObj.get("weather");
        JSONArray jsonArr = (JSONArray) JSONValue.parse(weather.toString());
        JSONObject obj = (JSONObject) JSONValue.parse(jsonArr.get(0).toString());
        return obj.get("description").toString();
    }

    /**
     * Get the current temperature for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the current temperature
     */
    public double getTemp(JSONObject jsonObj){
        Object main = jsonObj.get("main");
        JSONObject obj = (JSONObject) JSONValue.parse(main.toString());
        return (double) obj.get("temp");
    }

    /**
     * Get the feels like temperature for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the feels like temperature
     */
    public double getFeelsLike(JSONObject jsonObj){
        Object main = jsonObj.get("main");
        JSONObject obj = (JSONObject) JSONValue.parse(main.toString());
        return (double) obj.get("feels_like");
    }

    /**
     * Get the min temperature for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the min temperature
     */
    public double getTempMin(JSONObject jsonObj){
        Object main = jsonObj.get("main");
        JSONObject obj = (JSONObject) JSONValue.parse(main.toString());
        return (double) obj.get("temp_min");
    }

    /**
     * Get the max temperature for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the max temperature
     */
    public double getTempMax(JSONObject jsonObj){
        Object main = jsonObj.get("main");
        JSONObject obj = (JSONObject) JSONValue.parse(main.toString());
        return (double) obj.get("temp_max");
    }

    /**
     * Get the pressure for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the pressure
     */
    public long getPressure(JSONObject jsonObj){
        Object main = jsonObj.get("main");
        JSONObject obj = (JSONObject) JSONValue.parse(main.toString());
        return (long)obj.get("pressure");
    }

    /**
     * Get the humidity for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the humidity
     */
    public long getHumidity(JSONObject jsonObj){
        Object main = jsonObj.get("main");
        JSONObject obj = (JSONObject) JSONValue.parse(main.toString());
        return (long)obj.get("humidity");
    }

    /**
     * Get the wind speed for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the wind speed
     */
    public double getWindSpeed(JSONObject jsonObj){
        Object wind = jsonObj.get("wind");
        JSONObject obj = (JSONObject) JSONValue.parse(wind.toString());
        return (double)obj.get("speed");
    }

    /**
     * Get the wind direction for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the wind direction
     */
    public long getWindDirection(JSONObject jsonObj){
        Object wind = jsonObj.get("wind");
        JSONObject obj = (JSONObject) JSONValue.parse(wind.toString());
        return (long)obj.get("deg");
    }

    /**
     * Get the sunrise time for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the sunrise time
     */
    public String getSunrise(JSONObject jsonObj) {
        Object sys = jsonObj.get("sys");
        JSONObject obj = (JSONObject) JSONValue.parse(sys.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long epoch = (long)obj.get("sunrise");
        return sdf.format(new Date(epoch * 1000));
    }

    /**
     * Get the sunset time for the searched zip code
     *
     * @param jsonObj JSON object for the fetched weather info
     * @return the sunset time
     */
    public String getSunset(JSONObject jsonObj) {
        Object sys = jsonObj.get("sys");
        JSONObject obj = (JSONObject) JSONValue.parse(sys.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long epoch = (long)obj.get("sunset");
        return sdf.format(new Date(epoch * 1000));
    }
}
