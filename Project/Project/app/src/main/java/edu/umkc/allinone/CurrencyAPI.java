package edu.umkc.allinone;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Currency API class
 *
 * @author Jun Yang
 */

public class CurrencyAPI {

    /**
     * Fetch result from free.currconv.com via REST API
     *
     * @param fromCurrency from currency
     * @param toCurrency to currency
     *
     * @return JSON object for the fetched currency info
     */
    public JSONObject fetch(String fromCurrency, String toCurrency) throws IOException {
        String qry = fromCurrency + "_" + toCurrency;
        String endpoint = "https://free.currconv.com/api/v7/convert?q=" + qry + "&compact=ultra&apiKey=f52d3760f24d16a1eb21";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(endpoint).method("GET", null).build();
        Response response = client.newCall(request).execute();
        Object obj = JSONValue.parse(response.body().string());
        return (JSONObject) obj;
    }

    /**
     * Get the converted currency
     *
     * @param jsonObj JSON object for the fetched currency info
     * @return the converted currency
     */
    public Double getCurrencyRate(JSONObject jsonObj){
        Set keys = jsonObj.keySet();
        Iterator iterator = keys.iterator();
        Object currency = jsonObj.get(iterator.next());
        return (Double) currency;
    }
}
