package edu.umkc.allinone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Translate API class
 *
 * @author Jun Yang
 */

public class TranslateAPI {
    // Define a hashmap for language to language code mapping
    private Map<String, String> languagesMap = new HashMap<String, String>();

    /**
     * Constructor
     */
    public TranslateAPI(){
        loadLanguagesMap();
    }

    /**
     * Load mapping between language to language code
     */
    // Load the hashmap for language to language code
    public void loadLanguagesMap(){
        this.languagesMap.put("Afrikaans", "af");
        this.languagesMap.put("Amharic", "am");
        this.languagesMap.put("Arabic", "ar");
        this.languagesMap.put("Azerbaijani", "az");
        this.languagesMap.put("Bashkir", "ba");
        this.languagesMap.put("Belarusian", "be");
        this.languagesMap.put("Bulgarian", "bg");
        this.languagesMap.put("Bengali", "bn");
        this.languagesMap.put("Bosnian", "bs");
        this.languagesMap.put("Catalan", "ca");
        this.languagesMap.put("Cebuano", "ceb");
        this.languagesMap.put("Czech", "cs");
        this.languagesMap.put("Chuvash", "cv");
        this.languagesMap.put("Welsh", "cy");
        this.languagesMap.put("Danish", "da");
        this.languagesMap.put("German", "de");
        this.languagesMap.put("Greek", "el");
        this.languagesMap.put("English", "en");
        this.languagesMap.put("Esperanto", "eo");
        this.languagesMap.put("Spanish", "es");
        this.languagesMap.put("Estonian", "et");
        this.languagesMap.put("Basque", "eu");
        this.languagesMap.put("Persian", "fa");
        this.languagesMap.put("Finnish", "fi");
        this.languagesMap.put("French", "fr");
        this.languagesMap.put("Irish", "ga");
        this.languagesMap.put("Scottish Gaelic", "gd");
        this.languagesMap.put("Galician", "gl");
        this.languagesMap.put("Gujarati", "gu");
        this.languagesMap.put("Hebrew", "he");
        this.languagesMap.put("Hindi", "hi");
        this.languagesMap.put("Croatian", "hr");
        this.languagesMap.put("Haitian", "ht");
        this.languagesMap.put("Hungarian", "hu");
        this.languagesMap.put("Armenian", "hy");
        this.languagesMap.put("Indonesian", "id");
        this.languagesMap.put("Icelandic", "is");
        this.languagesMap.put("Italian", "it");
        this.languagesMap.put("Japanese", "ja");
        this.languagesMap.put("Javanese", "jv");
        this.languagesMap.put("Georgian", "ka");
        this.languagesMap.put("Kazakh", "kk");
        this.languagesMap.put("Khmer", "km");
        this.languagesMap.put("Kannada", "kn");
        this.languagesMap.put("Korean", "ko");
        this.languagesMap.put("Kyrgyz", "ky");
        this.languagesMap.put("Latin", "la");
        this.languagesMap.put("Luxembourgish", "lb");
        this.languagesMap.put("Lao", "lo");
        this.languagesMap.put("Lithuanian", "lt");
        this.languagesMap.put("Latvian", "lv");
        this.languagesMap.put("Malagasy", "mg");
        this.languagesMap.put("Mari", "mhr");
        this.languagesMap.put("Maori", "mi");
        this.languagesMap.put("Macedonian", "mk");
        this.languagesMap.put("Malayalam", "ml");
        this.languagesMap.put("Mongolian", "mn");
        this.languagesMap.put("Marathi", "mr");
        this.languagesMap.put("Hill Mari", "mrj");
        this.languagesMap.put("Malay", "ms");
        this.languagesMap.put("Maltese", "mt");
        this.languagesMap.put("Burmese", "my");
        this.languagesMap.put("Nepali", "ne");
        this.languagesMap.put("Dutch", "nl");
        this.languagesMap.put("Norwegian", "no");
        this.languagesMap.put("Punjabi", "pa");
        this.languagesMap.put("Papiamento", "pap");
        this.languagesMap.put("Polish", "pl");
        this.languagesMap.put("Portuguese", "pt");
        this.languagesMap.put("Romanian", "ro");
        this.languagesMap.put("Russian", "ru");
        this.languagesMap.put("Sinhalese", "si");
        this.languagesMap.put("Slovak", "sk");
        this.languagesMap.put("Slovenian", "sl");
        this.languagesMap.put("Albanian", "sq");
        this.languagesMap.put("Serbian", "sr");
        this.languagesMap.put("Sundanese", "su");
        this.languagesMap.put("Swedish", "sv");
        this.languagesMap.put("Swahili", "sw");
        this.languagesMap.put("Tamil", "ta");
        this.languagesMap.put("Telugu", "te");
        this.languagesMap.put("Tajik", "tg");
        this.languagesMap.put("Thai", "th");
        this.languagesMap.put("Tagalog", "tl");
        this.languagesMap.put("Turkish", "tr");
        this.languagesMap.put("Tatar", "tt");
        this.languagesMap.put("Udmurt", "udm");
        this.languagesMap.put("Ukrainian", "uk");
        this.languagesMap.put("Urdu", "ur");
        this.languagesMap.put("Uzbek", "uz");
        this.languagesMap.put("Vietnamese", "vi");
        this.languagesMap.put("Xhosa", "xh");
        this.languagesMap.put("Yiddish", "yi");
        this.languagesMap.put("Chinese", "zh");
    }

    /**
     * Fetch result from translate.yandex.net via REST API
     *
     * @param inputStr input string
     * @param fromLanguage from language
     * @param toLanguage to language
     *
     * @return JSON object for the fetched translated info
     */
    public JSONObject fetch(String inputStr, String fromLanguage, String toLanguage) throws IOException {
        String fromLanguageCode = this.languagesMap.get(fromLanguage);
        String toLanguageCode = this.languagesMap.get(toLanguage);
        String endpoint = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20151023T145251Z.bf1ca7097253ff7e.c0b0a88bea31ba51f72504cc0cc42cf891ed90d2&text=" + inputStr + "&lang=" + fromLanguageCode + "-" + toLanguageCode;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(endpoint).method("GET", null).build();
        Response response = client.newCall(request).execute();
        Object obj = JSONValue.parse(response.body().string());
        return (JSONObject) obj;
    }

    /**
     * Get the translated text
     *
     * @param jsonObj JSON object for the fetched translated info
     * @return the translated text
     */
    public String getTranslatedText(JSONObject jsonObj){
        Object name = jsonObj.get("text");
        JSONArray obj = (JSONArray) JSONValue.parse(name.toString());
        return obj.get(0).toString();
    }
}
