package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.R;

/**
 * Created by falcon on 17.01.17.
 */

public class RegistrationFragment extends Fragment implements View.OnClickListener {

    private EditText etRegLogin, etRegPassword, etRegEmail, etRegName, etRegSurname, etRegCity;
    private Button btnRegister;

    private String login, password, email, name, surname, city;

    private IOnMyRegisterClickListener registerClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        registerClickListener = (IOnMyRegisterClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        etRegLogin = (EditText)view.findViewById(R.id.etRegLogin);
        etRegPassword = (EditText)view.findViewById(R.id.etRegPassword);
        etRegEmail = (EditText)view.findViewById(R.id.etRegEmail);
        etRegName = (EditText)view.findViewById(R.id.etRegName);
        etRegSurname = (EditText)view.findViewById(R.id.etRegSurname);
        etRegCity = (EditText)view.findViewById(R.id.etRegCity);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                if (String.valueOf(etRegLogin.getText()).equals("") | String.valueOf(etRegPassword.getText()).equals("") | String.valueOf(etRegEmail.getText()).equals("")) {
                    Toast.makeText(getActivity(), "Введите данные в обязательные поля, которые отмечены *",
                            Toast.LENGTH_LONG).show();
                    etRegLogin.setHintTextColor(Color.RED);
                    etRegPassword.setHintTextColor(Color.RED);
                    etRegEmail.setHintTextColor(Color.RED);
                } else {
                    registerClickListener.onRegisterButtonClick(String.valueOf(etRegLogin.getText()),
                            String.valueOf(etRegPassword.getText()),
                            String.valueOf(etRegEmail.getText()),
                            String.valueOf(etRegName.getText()),
                            String.valueOf(etRegSurname.getText()),
                            String.valueOf(etRegCity.getText()));
                }
                break;
        }
    }

    public interface IOnMyRegisterClickListener {
        void onRegisterButtonClick(String login, String password, String email, String name,
                                   String surname, String city);
    }
}
