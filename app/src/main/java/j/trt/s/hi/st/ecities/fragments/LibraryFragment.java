package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.data.GetCityInfoResponse;
import j.trt.s.hi.st.ecities.data.GetCityInfoTask;

public class LibraryFragment extends ListFragment {

    private ArrayAdapter<String> libraryadapter;
    private IOnMyLibraryClickListener libraryClickListener;
    int city_id = 0;
    JSONObject cityinf = null;
    private String name = "";
    private String population = "";
    private String establishment = "";
    private String url = "";
    private String arms = "";
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
        libraryClickListener.onGameCityClick(position+1);
//        Log.d(Constants.LOG_TAG, "int position = " + position + "; long id = " + id);
//        //  don't forget about incrementation for position or id
//        city_id = position +1;
//        new GetCityInfoTask(this).execute(String.valueOf(city_id));
//    }
//
//    @Override
//    public void libraryCityInfo(String cityInfo) {
//        Log.d(Constants.LOG_TAG, "get city info = " + cityInfo);
//        try {
//            cityinf = new JSONObject(cityInfo);
//
//            name = cityinf.getString(Constants.CityInfo.NAME);
//            population = cityinf.getString(Constants.CityInfo.POPULATION);
//            establishment = cityinf.getString(Constants.CityInfo.ESTABLISHMENT);
//            url = cityinf.getString(Constants.CityInfo.URL);
//            arms = cityinf.getString(Constants.CityInfo.ARMS);
//
//            Log.d(Constants.LOG_TAG, "Library name: " + name);
//            Log.d(Constants.LOG_TAG, "Library population: " + population);
//            Log.d(Constants.LOG_TAG, "Library establishment: " + establishment);
//            Log.d(Constants.LOG_TAG, "Library url: " + url);
//            Log.d(Constants.LOG_TAG, "Library arms: " + arms);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e(Constants.LOG_TAG, "parsing cityInfo error: " + e.toString());
//        }
    }

    public interface IOnMyLibraryClickListener {
        void onGameCityClick(int position);
    }

}

