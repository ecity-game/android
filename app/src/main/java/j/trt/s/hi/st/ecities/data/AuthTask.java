package j.trt.s.hi.st.ecities.data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import j.trt.s.hi.st.ecities.Constants;

public class AuthTask extends AsyncTask<String, Void, String> {

    private AuthResponse delegate = null;

    public AuthTask(AuthResponse listener) {
        delegate = listener;
    }



    @Override
    protected String doInBackground(String... params) {
        Log.d(Constants.LOG_TAG, "auth params = " + params[0]);
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(Constants.URL.AUTH_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(Constants.URL.POST);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty(Constants.Authorization.AUTH, params[0]);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ProtocolException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String aBoolean) {
        if (delegate != null)
            delegate.authIsDone(aBoolean);
    }
}