package j.trt.s.hi.st.ecities.data;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.activities.MainActivity;

/**
 * Created by gertz on 20.01.17.
 */

public class LogoutTask extends AsyncTask<Void, Void, String> {

    private LogoutResponse delegate = null;
    public LogoutTask(LogoutResponse listener){
        delegate = listener;
    }


    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(Constants.LOGOUT);
            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();

        } catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }return null;
        }

        @Override
    protected void onPostExecute(String resp) {
        if(delegate != null)
            delegate.logoutResponse(resp);
    }
}
