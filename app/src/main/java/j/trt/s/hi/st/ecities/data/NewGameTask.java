package j.trt.s.hi.st.ecities.data;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import j.trt.s.hi.st.ecities.activities.MainActivity;


public class NewGameTask extends AsyncTask<String, Void, String> {
    private NewGameResponse delegate = null;
    private final String http = "http://ecity.org.ua:8080/game/new";
    public NewGameTask(NewGameResponse listener) {
        delegate = listener;
    }
    @Override
    protected String doInBackground(String... params) {
        StringBuffer buffer = new StringBuffer();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet;
        try {
            httpGet = new HttpGet(http);
            String auth = new String(Base64.encode((params[0] + ":" + params[1]).getBytes(), Base64.URL_SAFE | Base64.NO_WRAP));
            httpGet.addHeader("Authorization", "Basic " + auth);
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e(MainActivity.TAG, e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(MainActivity.TAG, e.toString());
        }
        return null;
    }
    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        if(delegate != null){
            delegate.newGameId(str);
        }
    }
}
