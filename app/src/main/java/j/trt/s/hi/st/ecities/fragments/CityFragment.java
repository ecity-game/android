package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.data.DownloadCityArms;

public class CityFragment extends Fragment implements View.OnClickListener {

    private String cityName, cityPopulation, cityEstablishment, cityUrl, cityArms;

    private ImageView ivCityFragmentPic;
    private TextView tvCityFragmentTitle, tvCityFragmentText, tvCityFragmentLink;

    private IOnMyCityInfoClickListener cityClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cityClickListener = (IOnMyCityInfoClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);

        ivCityFragmentPic = (ImageView) view.findViewById(R.id.ivCityFragmentPic);
        tvCityFragmentTitle = (TextView) view.findViewById(R.id.tvCityFragmentTitle);
        tvCityFragmentText = (TextView) view.findViewById(R.id.tvCityFragmentText);
        tvCityFragmentLink = (TextView) view.findViewById(R.id.tvCityFragmentLink);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            cityName = bundle.getString("cityName", "");
            cityPopulation = bundle.getString("cityPopulation", "");
            cityEstablishment = bundle.getString("cityEstablishment", "");
            cityUrl = bundle.getString("cityUrl", "");
            cityArms = bundle.getString("cityArms", "");
            new DownloadCityArms(ivCityFragmentPic).execute(cityArms);
            Log.v(Constants.LOG_TAG, "cityPopulation in fragment:" + cityPopulation);
            Log.v(Constants.LOG_TAG, "cityEstablishment in fragment:" + cityEstablishment);
        }

        tvCityFragmentTitle.setText(cityName);
        tvCityFragmentText.setText("Население " + cityPopulation + "\n" + "Год основания: " + cityEstablishment);
        tvCityFragmentLink.setText(cityUrl);
        tvCityFragmentLink.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        cityClickListener.onCityLinkClick(cityUrl);
    }

    public interface IOnMyCityInfoClickListener {
        void onCityLinkClick(String link);
    }
}

