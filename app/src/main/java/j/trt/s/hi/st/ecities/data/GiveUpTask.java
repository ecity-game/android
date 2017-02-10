package j.trt.s.hi.st.ecities.data;


import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.activities.MainActivity;

public class GiveUpTask extends AsyncTask<Void, Void, String> {

    private SendCityResponse delegate = null;

    public GiveUpTask(SendCityResponse listener){
        delegate = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuffer buffer = new StringBuffer();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        try {
            nameValuePairs.add(new BasicNameValuePair(Constants.GAME_ID, MainActivity.myGame.id));
            httpPost = new HttpPost(Constants.GIVE_UP_URL);
            httpPost.addHeader("Authorization", MainActivity.user.authCertificate);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Constants.UTF_8));
            HttpResponse response = client.execute(httpPost);
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
                } else {
                    Log.e(Constants.LOG_TAG, "Error in giveUp request");
                }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            String ee = e.toString();
            Log.e("Error", ee);
        } catch (IOException e) {
            e.printStackTrace();
            String ee = e.toString();
            Log.e("Error", ee);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (delegate != null) {
            delegate.sendCityResponse(s);
        }
    }
}
