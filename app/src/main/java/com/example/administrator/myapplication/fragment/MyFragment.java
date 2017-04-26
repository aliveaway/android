package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

/**
 *
 */
public class MyFragment extends Fragment {

    private static final String CONTENT = "content";
    private String content;

    public MyFragment(String content) {
        this.content = content;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        TextView tx = (TextView) view.findViewById(R.id.txt_content);
        tx.setText(content);
        return view;
    }
}
