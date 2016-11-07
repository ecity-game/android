package j.trt.s.hi.st.ecities.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.fragments.AuthFragment;
import j.trt.s.hi.st.ecities.fragments.GameFragment;
import j.trt.s.hi.st.ecities.fragments.MenuFragment;
import j.trt.s.hi.st.ecities.fragments.RulesFragment;

public class MainActivity extends AppCompatActivity implements AuthFragment.IOnMyEnterClickListener,
        MenuFragment.IOnMyMenuClickListener, GameFragment.IOnMyGameClickListener {
    private EditText etLogin, etPassword, etInputCity;

    Fragment authFragment, menuFragment, rulesFragment, gameFragment, cityFragment;

    FragmentTransaction fTrans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);

        authFragment = new AuthFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, authFragment);
        fTrans.commit();
    }

    @Override
    public void onEnterButtonClick() {
        etLogin = (EditText)findViewById(R.id.etLogin);
        etPassword = (EditText)findViewById(R.id.etPassword);

        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        if (!login.equals("") && !password.equals("")) {
            sendAuth(login, password);
        }

        else if (login.equals("")) {
            Toast.makeText(this, "Please enter login", Toast.LENGTH_SHORT).show();
        }

        else if (password.equals("")) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onNewGameButtonClick() {
        gameFragment = new GameFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, gameFragment);
        fTrans.addToBackStack("MenuFragment");
        fTrans.commit();
    }

    @Override
    public void onRulesButtonClick() {
        rulesFragment = new RulesFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, rulesFragment);
        fTrans.addToBackStack("MenuFragment");
        fTrans.commit();
    }

    @Override
    public void onSendButtonClick() {
        etInputCity = (EditText)findViewById(R.id.etInputCity);

        String inputCity = etInputCity.getText().toString();

        if (!inputCity.equals("")) {
            sendCity(inputCity);
        }
        else {
            Toast.makeText(this, inputCity + "Please enter a city", Toast.LENGTH_SHORT).show();
        }
    }

    //Send user authentication data to server
    private void sendAuth(String login, String password) {

        //TODO Write authentication server request

        Toast.makeText(this, "Welcone " + login, Toast.LENGTH_SHORT).show();

        //Launch menu when authtorized
        menuFragment = new MenuFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, menuFragment);
        fTrans.addToBackStack("AuthFragment");
        fTrans.commit();
    }

    //Send city name to server
    private void sendCity(String inputCity) {

        //TODO Write send city server request

        Toast.makeText(this, inputCity + " sent to server", Toast.LENGTH_SHORT).show();
    }

}