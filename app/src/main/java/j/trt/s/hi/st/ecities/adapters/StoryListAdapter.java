package j.trt.s.hi.st.ecities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import j.trt.s.hi.st.ecities.CityInfo;
import j.trt.s.hi.st.ecities.R;

public class StoryListAdapter extends BaseAdapter {
    private LinkedList<CityInfo> gameStory;
    private LayoutInflater mInflater;

    public StoryListAdapter(Context c, LinkedList<CityInfo> cities) {
        gameStory = cities;
        c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return gameStory.size();
    }

    @Override
    public CityInfo getItem(int i) {
        return gameStory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.list_element, viewGroup, false);
        TextView cityName = (TextView) view.findViewById(android.R.id.text1);

        CityInfo cityInfo = getItem(i);
        cityName.setText(cityInfo.getName());

        return view;
    }
}
