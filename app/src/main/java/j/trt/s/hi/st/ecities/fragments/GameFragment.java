package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;

import j.trt.s.hi.st.ecities.R;

public class GameFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnSend, btnGiveUp;
    private ListView lvCtites;

    private IOnMyGameClickListener gameClickListener;

    private LinkedList<String> cities = new LinkedList<String>();
    ArrayAdapter<String> adapter;

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
        lvCtites = (ListView)view.findViewById(R.id.lvCityGameList);

        btnSend.setOnClickListener(this);
        btnGiveUp.setOnClickListener(this);
        lvCtites.setOnItemClickListener(this);

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_element, cities);
        lvCtites.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSend:
                gameClickListener.onSendButtonClick();
                break;
            case R.id.btnGiveUp:
                gameClickListener.onGiveUpButtonClick();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        gameClickListener.onGameCityClick(cities.get(i));
    }

    public void addCity(String city) {
        cities.addFirst(city);
        adapter.notifyDataSetChanged();
    }

    public interface IOnMyGameClickListener {
        void onSendButtonClick();
        void onGiveUpButtonClick();
        void onGameCityClick(String city);
    }

}