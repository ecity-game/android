package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import j.trt.s.hi.st.ecities.R;

public class LibraryFragment extends ListFragment {

    private ArrayAdapter<String> libraryadapter;
    private IOnMyLibraryClickListener libraryClickListener;

    private String[] cities;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        libraryClickListener = (IOnMyLibraryClickListener) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            cities = bundle.getStringArray("library");
            libraryadapter = new ArrayAdapter<String>(getActivity(), R.layout.list_element, cities);
            setListAdapter(libraryadapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        libraryClickListener.onGameCityClick(cities[position]);
    }

    public interface IOnMyLibraryClickListener {
        void onGameCityClick(String city);
    }

}

