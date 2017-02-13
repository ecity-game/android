package j.trt.s.hi.st.ecities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ScoresListAdapter extends ArrayAdapter {

    public ScoresListAdapter(Context context, int resource, List<HighScore> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HighScore score = (HighScore)getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_element_scores, parent, false);

        TextView tvScoresNum = (TextView) convertView.findViewById(R.id.tv_scores_num);
        TextView tvScoresPlayer = (TextView) convertView.findViewById(R.id.tv_scores_player);
        TextView tvScoresVictory = (TextView) convertView.findViewById(R.id.tv_scores_victory);
        TextView tvScoresDefeat = (TextView) convertView.findViewById(R.id.tv_scores_defeat);
        TextView tvScoresCoeff = (TextView) convertView.findViewById(R.id.tv_scores_coefficient);

        if (score != null) {
            tvScoresNum.setText(String.valueOf(score.getNum()));
            tvScoresPlayer.setText(score.getPlayer());
            tvScoresVictory.setText(String.valueOf(score.getVictory()));
            tvScoresDefeat.setText(String.valueOf(score.getDefeat()));
            tvScoresCoeff.setText(String.valueOf(score.getCoeff()));
        }

        return convertView;
    }
}
