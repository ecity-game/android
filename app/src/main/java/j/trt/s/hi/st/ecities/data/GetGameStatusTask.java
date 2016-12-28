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

public class GetGameStatusTask extends AsyncTask<String, Void, String> {
    private static final String getGameStatusUrl = "http://ecity.org.ua:8080/game/status";
    private GetGameStatusResponse delegate = null;
    public GetGameStatusTask(GetGameStatusResponse listener) {
        delegate = listener;

    }


    @Override
    protected String doInBackground(String... params) {
        StringBuffer buffer = new StringBuffer();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet;
        try {
            httpGet = new HttpGet(getGameStatusUrl);
            String auth =new String(Base64.encode(( params[0] + ":" + params[1]).getBytes(),Base64.URL_SAFE|Base64.NO_WRAP));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        Log.d(MainActivity.TAG, "GetGameStatusTask " + s);
        delegate.returnGameStatus(s);
    }
}
