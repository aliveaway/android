package com.example.administrator.myapplication.fragment.newslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class NewListFragment extends Fragment {
    private FragmentManager fragmentManager;
    private List<Data> mData;
    private ListView list_news;

    public NewListFragment(FragmentManager fragmentManager, List<Data> mData) {
        this.fragmentManager = fragmentManager;
        this.mData = mData;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_newlist, container, false);
        list_news = (ListView) view.findViewById(R.id.list_news);
        MyAdapter myAdapter = new MyAdapter(mData, getActivity());
        list_news.setAdapter(myAdapter);

        list_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                NewContentFragment contentFragment = new NewContentFragment();
                Bundle b = new Bundle();
                b.putString("content", mData.get(position).getNew_content());
                contentFragment.setArguments(b);

                //获取Activity的控件
                TextView txt_title = ((NewListActivity) getActivity()).getTitleTextView();
                txt_title.setText(mData.get(position).getNew_content());

                //加上Fragment替换动画
//                ft.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);

                ft.replace(R.id.fl_content, contentFragment);
                //调用addToBackStack将Fragment添加到栈中
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        return view;
    }
}
