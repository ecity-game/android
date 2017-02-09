package j.trt.s.hi.st.ecities.data;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import j.trt.s.hi.st.ecities.Constants;

public class GetGameStoryTask extends AsyncTask <String, Void, String>{


    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(Constants.URL.LOGOUT);
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
//        if(delegate != null)
//            delegate.logoutResponse(resp);
    }
}
