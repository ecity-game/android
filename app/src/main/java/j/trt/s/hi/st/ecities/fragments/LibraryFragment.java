package j.trt.s.hi.st.ecities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

public class LibraryFragment extends ListFragment implements View.OnClickListener {
        private ArrayAdapter<String> libraryadapter;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String[] cities = bundle.getStringArray("library");
            libraryadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cities);
            setListAdapter(libraryadapter);
        }
    }
    @Override
    public void onClick(View v) {

    }

}

