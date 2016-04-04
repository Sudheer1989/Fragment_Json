package com.mytorch.sweety.fragment_json;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Sweety on 04-04-2016.
 */

//STEP 7 CREATE SEPERATE CLASS FOR AN FRAGMENT EXTENDING AND IMPLEMENT ONCREATEVIEW

public class Myfragment extends Fragment {

    TextView tv1, tv2, tv3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//STEP 7.1 USING THE INFALOT.INFLATOR LOAD UR FRAGMENT XML LAYPOY
        View v = inflater.inflate(R.layout.myfragment, null);
        tv1 = (TextView) v.findViewById(R.id.textView4);
        tv2 = (TextView) v.findViewById(R.id.textView5);
        tv3 = (TextView) v.findViewById(R.id.textView6);

        return v;
    }

//STEP 8 CREATE AN SEPRETAE METHOD FOR HOLDING HTE DATA TO SHOW IN THE FRAGMENT XML LAYOUT SOO ..
    public void myMethod(Contacts obj) {
        String fname=obj.getName();
        String femail=obj.getEmail();
        String fphone=obj.getPhone();
        tv1.setText(fname);
        tv2.setText(femail);
        tv3.setText(fphone);
    }
}
