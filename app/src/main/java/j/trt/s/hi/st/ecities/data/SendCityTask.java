package j.trt.s.hi.st.ecities.data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import j.trt.s.hi.st.ecities.activities.MainActivity;

public class SendCityTask extends AsyncTask<String, Void, String> {
    private static final String SERVER_URL = "http://ecity.org.ua:8080/city";
    private SendCityResponse delegate = null;
    public SendCityTask(SendCityResponse listener){
        delegate = listener;
    }


    @Override
    protected String doInBackground(String... params) {
            try {
                String value = params[0];
                String param = "?name=";
                String encodedUrl = SERVER_URL + param + URLEncoder.encode(value, "utf-8");
                URL url = new URL(encodedUrl);
                URLConnection connection = url.openConnection();
                connection.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e(MainActivity.TAG, e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(MainActivity.TAG, e.toString());
            }
            return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (delegate != null) {
            delegate.sendCityResponse(result);
        }
    }
}
