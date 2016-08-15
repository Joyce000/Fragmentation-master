package me.yokeyword.sample.demo_zhihu.ui.fragment.third.child.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo_zhihu.entity.Article;
import me.yokeyword.sample.demo_zhihu.base.BaseBackFragment;
import me.yokeyword.sample.demo_zhihu.ui.fragment.CycleFragment;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class FoodAdd extends BaseBackFragment {
    private static final String ARG_ITEM = "arg_item";

    private Article mArticle;

    private Toolbar mToolbar;
    private ImageView mImgDetail;
    private TextView mTvTitle;
    private TextView mFoodName;
    private TextView mFoodUnit;
    private TextView mUnitPrice;
    private Button  mFoodAdd;
    private Button  mFoodPictureAdd;
    public static FoodAdd newInstance(Article article) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, article);
        FoodAdd fragment = new FoodAdd();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticle = getArguments().getParcelable(ARG_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_third_food_add, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mImgDetail = (ImageView) view.findViewById(R.id.FoodPicture);
        mFoodName = (TextView) view.findViewById(R.id.FoodName);
        mFoodUnit = (TextView) view.findViewById(R.id.FoodUnit);
        mUnitPrice = (TextView) view.findViewById(R.id.UnitPrice);
        mFoodAdd = (Button) view.findViewById(R.id.FoodAdd);
        mFoodPictureAdd = (Button) view.findViewById(R.id.FoodPictureAdd);
//        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
//        mToolbar.setTitle("");
//        initToolbarNav(mToolbar);
        mImgDetail.setImageResource(mArticle.getImgRes());
        mFoodName.setText(mArticle.getFoodName());
        mFoodUnit.setText(mArticle.getFoodUnit());
        mUnitPrice.setText(mArticle.getUnitPrice());



        mFoodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(_mActivity,mArticle.getUnitPrice()+"55555", Toast.LENGTH_SHORT).show();
            }
        });

        mImgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(1));
            }
        });
    }
}
