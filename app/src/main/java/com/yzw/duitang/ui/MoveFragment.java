package com.yzw.duitang.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.yzw.duitang.R;
import com.yzw.duitang.adapter.StaggerItemAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yzw on 2016/9/6.
 */
public class MoveFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @InjectView(R.id.stagger_view)
    StaggeredGridView mStaggeredGridView;

    @InjectView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stagger, container, false);
        ButterKnife.inject(this, view);
        handler = new Handler();
        initView();
        return view;
    }

    private void initView() {
        mStaggeredGridView.setAdapter(new StaggerItemAdapter(getActivity(), true));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), getString(R.string.refresh_success), Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}
