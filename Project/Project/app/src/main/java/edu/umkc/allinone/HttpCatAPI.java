package edu.umkc.allinone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * HTTP cat API class
 *
 * @author Jun Yang
 */

public class HttpCatAPI {

    /**
     * Fetch image from http.cat via REST API
     *
     * @param httpCode http code
     *
     * @return String encoded string of bitmap
     */
    public String fetch(int httpCode) throws IOException {
        InputStream in = new java.net.URL("https://http.cat/" + httpCode).openStream();
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        return bitMapToString(bitmap);
    }

    /**
     * Convert bitmap to String
     * @param bitmap the bitmap object
     *
     * @@return the string of bitmap object
     */
    public String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }

    /**
     * Convert String to bitmap
     * @param encodedString of bitmap object
     *
     * @@return the bitmap object
     */
    public Bitmap stringToBitMap(String encodedString){
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte,0, encodeByte.length);
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
