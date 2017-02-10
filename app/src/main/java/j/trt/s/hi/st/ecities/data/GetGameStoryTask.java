package j.trt.s.hi.st.ecities.data;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.activities.MainActivity;

public class GetGameStoryTask extends AsyncTask<String, Void, String> {

    private GetGameStoryResponse delegate = null;

    public GetGameStoryTask(GetGameStoryResponse listener) {
        delegate = listener;
    }


    @Override
    protected String doInBackground(String... params) {
        URL url;
        String response = "";
        HttpURLConnection conn = null;
        try {
            url = new URL(Constants.GAME_STORY + "?game_id=" + params[1]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.addRequestProperty("Authorization", params[0]);
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                    return response.toString();
                }
            } else {
                response = "";

                throw new HttpException(responseCode + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (delegate != null) {
            delegate.getGameStoryResponse(result);
        }
    }
}