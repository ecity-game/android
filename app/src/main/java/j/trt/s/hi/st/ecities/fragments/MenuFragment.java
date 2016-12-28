package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import j.trt.s.hi.st.ecities.R;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private Button btnNewGame, btnContinue, btnRules, btnLibrary;
    private EditText etInputCity;

    IOnMyMenuClickListener menuClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        menuClickListener = (IOnMyMenuClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btnNewGame = (Button)view.findViewById(R.id.btnNewGame);
        btnContinue = (Button)view.findViewById(R.id.btnContinue);
        btnRules = (Button)view.findViewById(R.id.btnRules);
        btnLibrary = (Button)view.findViewById(R.id.btnLibrary);
        etInputCity = (EditText)view.findViewById(R.id.etInputCity);


        btnNewGame.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        btnRules.setOnClickListener(this);
        btnLibrary.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNewGame:
                menuClickListener.onNewGameButtonClick();
                break;

            case R.id.btnContinue:
                menuClickListener.onContinueButtonClick();
                break;

            case R.id.btnRules:
                menuClickListener.onRulesButtonClick();
                break;

            case R.id.btnLibrary:
                menuClickListener.onLibraryButtonClick();
                break;
        }
    }

    public interface IOnMyMenuClickListener {
        void onNewGameButtonClick();
        void onContinueButtonClick();
        void onRulesButtonClick();
        void onLibraryButtonClick();
    }

}
