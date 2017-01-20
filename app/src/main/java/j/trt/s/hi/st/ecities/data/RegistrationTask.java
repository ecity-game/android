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

/**
 * Created by gertz on 20.01.17.
 */

public class RegistrationTask extends AsyncTask<String, Void, String> {

private RegistrationResponse delegate = null;

public RegistrationTask (RegistrationResponse listener){
        delegate=listener;
        }


@Override
protected String doInBackground(String...params){
        StringBuffer buffer=new StringBuffer();
        HttpClient client=new DefaultHttpClient();
        HttpPost httpPost;
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(2);

        try{
        nameValuePairs.add(new BasicNameValuePair(Constants.Registration.LOGIN, params[0] ));
        nameValuePairs.add(new BasicNameValuePair(Constants.Registration.PASSWORD, params[1] ));
        nameValuePairs.add(new BasicNameValuePair(Constants.Registration.EMAIL, params[2] ));
        nameValuePairs.add(new BasicNameValuePair(Constants.Registration.FIRST_NAME, params[3] ));
        nameValuePairs.add(new BasicNameValuePair(Constants.Registration.LAST_NAME, params[4] ));
        nameValuePairs.add(new BasicNameValuePair(Constants.Registration.CITY_LIVE, params[5] ));
        httpPost=new HttpPost(Constants.URL.REGISTRATION);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,Constants.UTF_8));
        HttpResponse response=client.execute(httpPost);
        StatusLine statusLine=response.getStatusLine();
        int statusCode=statusLine.getStatusCode();
        Log.d(Constants.LOG_TAG,String.valueOf(statusCode));
        HttpEntity entity=response.getEntity();
        InputStream content=entity.getContent();
        BufferedReader reader=new BufferedReader(new InputStreamReader(content));
        String line;
        while((line=reader.readLine())!=null){
        buffer.append(line);
        }
        return buffer.toString();
//                if (statusCode == 200) {
//                    HttpEntity entity = response.getEntity();
//                    InputStream content = entity.getContent();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        buffer.append(line);
//                    }
//                    return buffer.toString();
//                } else {
//                    Log.e(ParseJSON.class.toString(), "Failed to download file");
//                }
        }catch(ClientProtocolException e){
        e.printStackTrace();
        String ee=e.toString();
        Log.e("Error",ee);
        }catch(IOException e){
        e.printStackTrace();
        String ee=e.toString();
        Log.e("Error",ee);
        }
        return null;
        }


@Override
protected void onPostExecute(String result){
        super.onPostExecute(result);
        if(delegate != null){
        delegate.registrationComplete( result );
        }
        }
        }
