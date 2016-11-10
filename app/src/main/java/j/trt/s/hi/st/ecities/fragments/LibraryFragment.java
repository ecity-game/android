package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import j.trt.s.hi.st.ecities.R;

public class LibraryFragment extends ListFragment implements View.OnClickListener {

    private Button btnUpdateCityList;

    LibraryFragment.IOnMyCityListClick cityListClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cityListClickListener = (IOnMyCityListClick) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        btnUpdateCityList = (Button)view.findViewById(R.id.btnUpdateCities);

        btnUpdateCityList.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        cityListClickListener.onUpdateCityListButtonClick();
    }

    public interface IOnMyCityListClick {
        void onUpdateCityListButtonClick();
    }
}

