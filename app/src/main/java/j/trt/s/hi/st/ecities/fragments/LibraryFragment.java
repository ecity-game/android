package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import j.trt.s.hi.st.ecities.CityInfo;
import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.adapters.LibraryAdapter;
import j.trt.s.hi.st.ecities.data.GetCityInfoResponse;
import j.trt.s.hi.st.ecities.data.GetCityInfoTask;

public class LibraryFragment extends ListFragment {

    private LibraryAdapter libraryadapter;
    private IOnMyLibraryClickListener libraryClickListener;
    private LinkedList<CityInfo> listCities = new LinkedList<CityInfo>();

    public static LibraryFragment newInstance(LinkedList<CityInfo> libraryList) {
        LibraryFragment libraryFragment = new LibraryFragment();
        Bundle args = new Bundle();
        for (int i = 0; i < libraryList.size(); i++) {
            args.putParcelable("CityInfo " + i, libraryList.get(i));
        }
        libraryFragment.setArguments(args);
        return libraryFragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        libraryClickListener = (IOnMyLibraryClickListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            for (int i = 0; i < getArguments().size(); i++) {
                CityInfo cityInfo = getArguments().getParcelable("CityInfo " + i);
                if (cityInfo != null) {
                    listCities.add(cityInfo);
                }
            }

        }
        libraryadapter = new LibraryAdapter(getActivity(), listCities);
        setListAdapter(libraryadapter);
        return view;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            cities = bundle.getStringArray("library");
//            libraryadapter = new ArrayAdapter<String>(getActivity(), R.layout.list_element, cities);
//            setListAdapter(libraryadapter);
//        }
//    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        CityInfo clickedCity = (CityInfo) l.getItemAtPosition(position);
        libraryClickListener.onGameCityClick(String.valueOf(clickedCity.id));
    }

    public interface IOnMyLibraryClickListener {
        void onGameCityClick(String position);
    }

}

