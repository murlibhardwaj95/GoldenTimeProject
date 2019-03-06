package com.goldentime.syon.goldentime.question;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.goldentime.syon.goldentime.BuildConfig;
import com.goldentime.syon.goldentime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrueFalseFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".FRAGMENT_TAG";
    ImageView iv_home,iv_back;
    public TrueFalseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =  inflater.inflate(R.layout.fragment_true_false, container, false);
        iv_home = view.findViewById(R.id.iv_home);
        iv_back = view.findViewById(R.id.iv_back);
        return view;
    }

}
