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

import j.trt.s.hi.st.ecities.R;

public class GameFragment extends Fragment implements View.OnClickListener {

    private Button btnSend, btnGiveUp;

    EditText etInputCity;

    private IOnMyGameClickListener gameClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        gameClickListener = (IOnMyGameClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);


        btnSend = (Button)view.findViewById(R.id.btnSend);
        btnGiveUp = (Button)view.findViewById(R.id.btnGiveUp);
        etInputCity = (EditText)view.findViewById(R.id.etInputCity);

        btnSend.setOnClickListener(this);
        btnGiveUp.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSend:
                gameClickListener.onSendButtonClick();
                break;
            case R.id.btnGiveUp:
                break;
        }

    }

    public interface IOnMyGameClickListener {
        void onSendButtonClick();
    }

}
