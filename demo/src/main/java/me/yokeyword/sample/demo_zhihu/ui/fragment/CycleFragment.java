package me.yokeyword.sample.demo_zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo_zhihu.base.BaseBackFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class CycleFragment extends BaseBackFragment {
    private static final String ARG_NUMBER = "arg_number";

   private Toolbar mToolbar;
//    private TextView mTvName;
//    private Button mBtnNext, mBtnNextWithFinish;
    private Button mFoodAdd;
    private Button mFoodPictureAdd;

    private int mNumber;

    public static CycleFragment newInstance(int number) {
        CycleFragment fragment = new CycleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mNumber = args.getInt(ARG_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_third_food_add, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mFoodAdd = (Button) view.findViewById(R.id.FoodAdd);
        mFoodPictureAdd = (Button) view.findViewById(R.id.FoodPictureAdd);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        mFoodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(_mActivity, "Toast text, normal", Toast.LENGTH_SHORT).show();
            }
        });
//        mTvName = (TextView) view.findViewById(R.id.tv_name);
//        mBtnNext = (Button) view.findViewById(R.id.btn_next);
//        mBtnNextWithFinish = (Button) view.findViewById(R.id.btn_next_with_finish);
//        String title = "循环Fragment" + mNumber;
//       mToolbar.setTitle(title);
        initToolbarNav(mToolbar);
//        mTvName.setText(title);
//        mBtnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                start(CycleFragment.newInstance(mNumber + 1));
//            }
//        });
//        mBtnNextWithFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startWithPop(CycleFragment.newInstance(mNumber + 1));
//            }
//        });
    }
}
