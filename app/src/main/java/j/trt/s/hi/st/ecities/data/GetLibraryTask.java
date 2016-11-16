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

import j.trt.s.hi.st.ecities.activities.MainActivity;

public class GetLibraryTask extends AsyncTask<Void, Void, String[]> {
   private final String LIBRARY_URL = "http://ecity.org.ua:8080/names";
    int citiesCounter = 0;
    private GetLibraryResponse delegate = null;
    public GetLibraryTask(GetLibraryResponse listener){
        delegate = listener;
    }
    @Override
    protected String[] doInBackground(Void... params) {
        try {
            URL url = new URL(LIBRARY_URL);
            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            JSONObject cityObj;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            try {
                JSONArray array = new JSONArray(buffer.toString());
                citiesCounter = array.length();
                String[] cities = new String[citiesCounter];
                if(array != null){
                    for(int i = 0; i < citiesCounter; i++){
                        cityObj = array.getJSONObject(i);
                        cities[i] = cityObj.getString("name");
                    }
                }
                return cities;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String[] library) {
        if(delegate != null)
            delegate.getLibrary(library);
    }
}
