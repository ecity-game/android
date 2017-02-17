package j.trt.s.hi.st.ecities.data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import j.trt.s.hi.st.ecities.Constants;

public class GetCityInfoTask extends AsyncTask <String, Void, String> {

    private GetCityInfoResponse delegate = null;
    public GetCityInfoTask(GetCityInfoResponse listener) {
        delegate = listener;
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(Constants.GET_CITY + params[0]);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(Constants.LOG_TAG, "get city info = " + s);
        if(delegate != null){
            delegate.libraryCityInfo(s);
        }
    }
}