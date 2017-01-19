package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import j.trt.s.hi.st.ecities.R;

/**
 * Created by falcon on 04.11.16.
 */

public class AuthFragment extends Fragment implements View.OnClickListener {

    private TextView tvRegiser;
    private Button btnEnter;

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

        tvRegiser.setOnClickListener(this);
        btnEnter.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEnter:
                enterClickListener.onEnterButtonClick();
                break;
            case R.id.tvRegister:
                enterClickListener.onRegistrationTextClick();
                break;
        }
    }

    public interface IOnMyEnterClickListener {
        void onEnterButtonClick();
        void onRegistrationTextClick();
    }
}