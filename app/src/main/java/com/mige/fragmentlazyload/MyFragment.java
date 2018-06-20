package com.mige.fragmentlazyload;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/6/20.
 */

public class MyFragment extends BaseFragment {

    public static final String PAGE_NO = "PAGE_NO";

    private int page_no;
    /**
     * 初始化View完毕的标记
     */
    private boolean isPrepared;

    public static Fragment newInstance(int page_no){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_NO ,page_no);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        page_no = getArguments().getInt(PAGE_NO ,0);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView view=new TextView(getActivity());
        view.setGravity(Gravity.CENTER);
        view.setTextColor(Color.BLACK);
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18f);
        view.setText("Fragment"+page_no);
        isPrepared=true;
        lazyLoad();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private ProgressDialog dialog;

    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }
        dialog=ProgressDialog.show(getActivity(),"","加载中，请稍后...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),"加载完毕！",Toast.LENGTH_SHORT).show();
                ((TextView)getView()).setText(""+getArguments().getInt("PAGE_NO"));
                dialog.dismiss();
            }
        },3000);
        //填充各控件的数据
    }
}
