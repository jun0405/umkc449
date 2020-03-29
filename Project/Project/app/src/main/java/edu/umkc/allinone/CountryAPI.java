package edu.umkc.allinone;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Country API class
 *
 * @author Jun Yang
 */

public class CountryAPI {
    /**
     * Fetch result from restcountries.eu via REST API
     *
     * @param countryCode country code
     *
     * @return JSON object for the fetched currency info
     */
    public JSONObject fetch(String countryCode) throws IOException {
        String endpoint = "https://restcountries.eu/rest/v2/alpha/" + countryCode;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(endpoint).method("GET", null).build();
        Response response = client.newCall(request).execute();
        Object obj = JSONValue.parse(response.body().string());
        return (JSONObject) obj;
    }

    /**
     * Get the country name
     *
     * @param jsonObj JSON object for the fetched country info
     * @return the country name
     */
    public String getCountryName(JSONObject jsonObj){
        Object name = jsonObj.get("name");
        return name.toString();
    }

    /**
     * Get the country capital
     *
     * @param jsonObj JSON object for the fetched country info
     * @return the country capital
     */
    public String getCapital(JSONObject jsonObj){
        Object name = jsonObj.get("capital");
        return name.toString();
    }

    /**
     * Get the country region
     *
     * @param jsonObj JSON object for the fetched country info
     * @return the country region
     */
    public String getRegion(JSONObject jsonObj){
        Object region = jsonObj.get("region");
        return region.toString();
    }

    /**
     * Get the country subregion
     *
     * @param jsonObj JSON object for the fetched country info
     * @return the country subregion
     */
    public String getSubregion(JSONObject jsonObj){
        Object subregion = jsonObj.get("subregion");
        return subregion.toString();
    }

    /**
     * Get the country demonym
     *
     * @param jsonObj JSON object for the fetched country info
     * @return the country demonym
     */
    public String getDemonym(JSONObject jsonObj){
        Object demonym = jsonObj.get("demonym");
        return demonym.toString();
    }

    /**
     * Get the country area in square kilometre
     *
     * @param jsonObj JSON object for the fetched country info
     * @return the country area in square kilometre
     */
    public Double getArea(JSONObject jsonObj){
        Object area = jsonObj.get("area");
        return (Double) area;
    }
}
