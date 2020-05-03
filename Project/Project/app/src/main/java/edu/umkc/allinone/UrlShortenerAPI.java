package edu.umkc.allinone;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Url shortener API class
 *
 * @author Jun Yang
 */

public class UrlShortenerAPI {
    /**
     * Fetch result from rel.ink via REST API
     *
     * @return String shortener URL
     */
    public String fetch(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("url", url).build();
        Request request = new Request.Builder().url("https://rel.ink/api/links/").post(formBody).build();
        JSONObject obj = null;
        try {
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "https://rel.ink/" + obj.get("hashid").toString();
    }
}
