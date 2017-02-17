package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

import j.trt.s.hi.st.ecities.CityInfo;
import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.adapters.LibraryAdapter;
import j.trt.s.hi.st.ecities.adapters.StoryListAdapter;

public class GameFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnSend, btnGiveUp;
    private ListView lvCtites;
    private TextView tvOpponentTurn;
    private StoryListAdapter storyListAdapter;
    private IOnMyGameClickListener gameClickListener;

    private LinkedList<String> cities = new LinkedList<String>();
    private LinkedList<CityInfo> savedCities = new LinkedList<CityInfo>();
    private ArrayAdapter<String> adapter;

    public static GameFragment newInstance(CityInfo cityInfo) {
        GameFragment fragmentGame = new GameFragment();
        Bundle args = new Bundle();
        args.putParcelable("CityInfo", cityInfo);
        fragmentGame.setArguments(args);
        return fragmentGame;
    }

    public static GameFragment newInstance(LinkedList<CityInfo> cityList) {
        GameFragment fragmentGame = new GameFragment();
        Bundle args = new Bundle();
        for (int i = 0; i < cityList.size(); i++) {
            args.putParcelable("CityInfo " + i, cityList.get(i));
        }
        fragmentGame.setArguments(args);
        return fragmentGame;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        gameClickListener = (IOnMyGameClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        btnSend = (Button) view.findViewById(R.id.btnSend);
        btnGiveUp = (Button) view.findViewById(R.id.btnGiveUp);
        tvOpponentTurn = (TextView) view.findViewById(R.id.tvOpponentTurn);
        lvCtites = (ListView) view.findViewById(R.id.lvCityGameList);

        btnSend.setOnClickListener(this);
        btnGiveUp.setOnClickListener(this);
//        lvCtites.setOnItemClickListener(this);

        if (getArguments() != null) {
            for (int i = 0; i < getArguments().size(); i++) {
                CityInfo cityInfo = getArguments().getParcelable("CityInfo " + i);
                if (cityInfo != null) {
//                    cities.add(cityInfo.getName());
                    savedCities.addFirst(cityInfo);
                }
            }

            if (savedCities.size() > 0) {
                String opponentTurn = savedCities.getFirst().getName();
                SpannableStringBuilder sb = new SpannableStringBuilder(opponentTurn);
                ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.textSecondaryColor));
                String lastChar = savedCities.getLast().getLastChar().toLowerCase();
                int last = opponentTurn.length();

                if (!String.valueOf(opponentTurn.charAt(last - 1)).equals(lastChar))
                    last -= 1;
                else if (!String.valueOf(opponentTurn.charAt(last - 1)).equals(lastChar))
                    last -= 2;

                sb.setSpan(fcs, last - 1, last, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                tvOpponentTurn.setText(sb);
            }
        }

//        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_element, cities);
//        lvCtites.setAdapter(adapter);

        storyListAdapter = new StoryListAdapter(getActivity(), savedCities);
        lvCtites.setAdapter(storyListAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSend:
                gameClickListener.onSendButtonClick();
                break;
            case R.id.btnGiveUp:
                gameClickListener.onGiveUpButtonClick();
                break;
        }
    }

    /**
     * City List click
     *
     * @param i number of City in cityList
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CityInfo city = (CityInfo) adapterView.getItemAtPosition(i);
        gameClickListener.onGameCityClick(String.valueOf(city.id));
    }

    public void addCity(CityInfo city) {
        savedCities.addFirst(city);
//        cities.addFirst(city.name);
//        adapter.notifyDataSetChanged();
        storyListAdapter.notifyDataSetChanged();
    }

    public interface IOnMyGameClickListener {
        void onSendButtonClick();

        void onGiveUpButtonClick();

        void onGameCityClick(String city);
    }

}