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

public class GetLibraryTask extends AsyncTask<Void, Void, String> {
    int citiesCounter = 0;
    private GetLibraryResponse delegate = null;
    public GetLibraryTask(GetLibraryResponse listener){
        delegate = listener;
    }
    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(Constants.LIBRARY_URL);
            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                return buffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String library) {
        if(delegate != null)
            delegate.getLibrary(library);
    }
}
