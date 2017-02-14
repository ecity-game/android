package j.trt.s.hi.st.ecities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.LinkedList;

import j.trt.s.hi.st.ecities.HighScore;
import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.ScoresListAdapter;


public class ScoresFragment extends Fragment {

    private LinkedList<HighScore> scoresList = new LinkedList<>();
    private ScoresListAdapter adapter;

    private ListView lvScores;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scores, container, false);

        lvScores = (ListView)view.findViewById(R.id.lvScores);

        //Set sample high scores
        scoresList.add(new HighScore(1, "the_best", 75, 45, 0.63));
        scoresList.add(new HighScore(2, "totosha", 65, 56, 0.54));
        scoresList.add(new HighScore(3, "oleg45", 51, 45, 0.53));
        scoresList.add(new HighScore(4, "rosa", 35, 44, 0.44));
        scoresList.add(new HighScore(5, "marinka", 20, 46, 0.30));
        scoresList.add(new HighScore(6, "bon-bon", 15, 47, 0.24));
        scoresList.add(new HighScore(7, "fromUkraine", 20, 65, 0.24));
        scoresList.add(new HighScore(8, "param-pam", 31, 99, 0.24));
        scoresList.add(new HighScore(9, "partyBoy", 13, 43, 0.23));
        scoresList.add(new HighScore(10, "sky", 20, 76, 0.21));

        adapter = new ScoresListAdapter(getActivity(), R.layout.list_element_scores, scoresList);
        lvScores.setAdapter(adapter);

        return view;
    }
}
