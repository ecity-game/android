package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.R;


public class AuthFragment extends Fragment implements View.OnClickListener {

    private TextView tvRegiser;
    private Button btnEnter;
    private EditText etLogin, etPassword;
    private CheckBox cbRememberUser;

    private IOnMyEnterClickListener enterClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        enterClickListener = (IOnMyEnterClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, container, false);

        tvRegiser = (TextView)view.findViewById(R.id.tvRegister);
        btnEnter = (Button)view.findViewById(R.id.btnEnter);
        etLogin = (EditText)view.findViewById(R.id.etLogin);
        etPassword = (EditText)view.findViewById(R.id.etPassword);
        cbRememberUser = (CheckBox)view.findViewById(R.id.cbRememberUser);

        tvRegiser.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
        cbRememberUser.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String login = bundle.getString("login", "");
            String password = bundle.getString("pass", "");
            boolean checked = bundle.getBoolean("checked", false);
            etLogin.setText(login);
            etPassword.setText(password);
            cbRememberUser.setChecked(checked);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEnter:
                enterClickListener.onEnterButtonClick(cbRememberUser.isChecked());
                break;
            case R.id.tvRegister:
                enterClickListener.onRegistrationTextClick();
                break;
        }
    }

    public interface IOnMyEnterClickListener {
        void onEnterButtonClick(boolean c);
        void onRegistrationTextClick();
    }
}