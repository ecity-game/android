package j.trt.s.hi.st.ecities.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.data.AsyncResponse;
import j.trt.s.hi.st.ecities.data.AuthTask;
import j.trt.s.hi.st.ecities.data.NewGameRequest;
import j.trt.s.hi.st.ecities.data.NewGameResponse;
import j.trt.s.hi.st.ecities.fragments.AuthFragment;
import j.trt.s.hi.st.ecities.fragments.GameFragment;
import j.trt.s.hi.st.ecities.fragments.MenuFragment;

public class MainActivity extends AppCompatActivity implements AuthFragment.IOnMyEnterClickListener,
        MenuFragment.IOnMyMenuClickListener, GameFragment.IOnMyGameClickListener, AsyncResponse, NewGameResponse {
    private EditText etLogin, etPassword, etInputCity;

    Fragment authFragment, menuFragment, gameFragment, cityFragment;
    FragmentTransaction fTrans;

    public static final String TAG = "ECityTAG";
    private String[] authData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        authFragment = new AuthFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, authFragment);
        fTrans.commit();
        authData = new String[2];
    }

    @Override
    public void onEnterButtonClick() {
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);

        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();
        authData[0] = login;
        authData[1] = password;

        if (!login.equals("") && !password.equals("")) {
            sendAuth();
        } else if (login.equals("")) {
            Toast.makeText(this, "Please enter login", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onNewGameButtonClick() {
        new NewGameRequest(this).execute(authData);
    }

    @Override
    public void onSendButtonClick() {
        etInputCity = (EditText) findViewById(R.id.etInputCity);
        String inputCity = etInputCity.getText().toString();
        if (!inputCity.equals("")) {
            sendCity(inputCity);
        } else {
            Toast.makeText(this, inputCity + "Please enter a city", Toast.LENGTH_SHORT).show();
        }
    }

    //Send user authentication data to server
    private void sendAuth() {
        //TODO Write authentication server request
        new AuthTask(this).execute(authData);
        Toast.makeText(this, "Welcome " + authData[0], Toast.LENGTH_SHORT).show();
    }

    //Send city name to server
    private void sendCity(String inputCity) {

        //TODO Write send city server request

        Toast.makeText(this, inputCity + " sent to server", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void authIsDone(Boolean output) {
        if(output == true){
            //Launch menu when authtorized
            menuFragment = new MenuFragment();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.flFragmentContainer, menuFragment);
            fTrans.addToBackStack("AuthFragment");
            fTrans.commit();
        }else{
            Toast.makeText(this, "Authentication Error", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void newGameId(String newGameId) {
        try {
            JSONObject jsonId = new JSONObject(newGameId);
            String id = jsonId.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        gameFragment = new GameFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, gameFragment);
        fTrans.addToBackStack("MenuFragment");
        fTrans.commit();
    }
}