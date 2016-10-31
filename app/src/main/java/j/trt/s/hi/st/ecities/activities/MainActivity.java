package j.trt.s.hi.st.ecities.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.fragments.GameFragment;
import j.trt.s.hi.st.ecities.fragments.MenuFragment;

public class MainActivity extends AppCompatActivity implements MenuFragment.IOnMyMenuClickListener,
        GameFragment.IOnMyGameClickListener {
    private EditText etInputCity;

    Fragment menuFragment, gameFragment, cityFragment;

    FragmentTransaction fTrans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        etInputCity = (EditText)findViewById(R.id.etInputCity);

        super.onCreate(savedInstanceState);

        menuFragment = new MenuFragment();

        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, menuFragment);
        fTrans.commit();
    }

    @Override
    public void onNewGameButtonClick() {
        gameFragment = new GameFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, gameFragment);
        fTrans.addToBackStack("MenuFragment");
        fTrans.commit();
    }

    //onSendButtonClick in progress
    @Override
    public void onSendButtonClick() {
//        if(etInputCity.getText() != null) {
//            String inputCity = etInputCity.getText().toString();
//            sendCity(inputCity);
//        }
//        else Toast.makeText(this, "Введите ответ", Toast.LENGTH_SHORT).show();
    }

    //Send data to server
    private void sendCity(String inputCity) {
    }

}