package me.yokeyword.sample.demo_zhihu.ui.fragment.third.child.child;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo_zhihu.MainActivity;
import me.yokeyword.sample.demo_zhihu.adapter.FirstHomeAdapter;
import me.yokeyword.sample.demo_zhihu.adapter.ThirdContentAdapter;
import me.yokeyword.sample.demo_zhihu.entity.Article;
import me.yokeyword.sample.demo_zhihu.event.TabSelectedEvent;
import me.yokeyword.sample.demo_zhihu.helper.DetailTransition;
import me.yokeyword.sample.demo_zhihu.listener.OnItemClickListener;
import me.yokeyword.sample.demo_zhihu.base.BaseFragment;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class ContentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_MENU = "arg_menu";
    private String mMenu;
//    private Toolbar mToolbar;
    private RecyclerView mRecy;
    private SwipeRefreshLayout mRefreshLayout;
//    private FloatingActionButton mFab;

    private ThirdContentAdapter mAdapter;

    private boolean mInAtTop = true;
    private int mScrollTotal;

//    String FoodName,String FoodUnit,String FoodNum,String UnitPrice, int imgRes
    private String[]FoodName = new String[]{
        "韭菜",
        "猪肉",
        "虾仁",
        "白菜",
        "豆腐"
    };
    private String[] FoodUnit = new String[]{
            "1个/两",
            "2个/两",
            "3个/两",
            "4个/两",
            "5个/两"
    };
    private String[] FoodNum = new String[]{
            "11",
            "22",
            "33",
            "44",
            "55",

    };
    private String[] UnitPrice = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
    };

    private int[] mImgRes = new int[]{
            R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_1, R.drawable.img_2
    };


    public static ContentFragment newInstance(String menu) {

        Bundle args = new Bundle();
        args.putString(ARG_MENU, menu);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mMenu = args.getString(ARG_MENU);
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_third_menu_content, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
//        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
//        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

//        mToolbar.setTitle("餐单流水");
//        initToolbarMenu(mToolbar);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);
//        mRefreshLayout.onTabSelectedEvent(this);
        mAdapter = new ThirdContentAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                FoodAdd fragment = FoodAdd.newInstance(mAdapter.getItem(position));

                // 这里是使用SharedElement的用例
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setExitTransition(new Fade());
                    setSharedElementReturnTransition(new DetailTransition());
//                    fragment.setEnterTransition(new Fade());
                    fragment.setSharedElementEnterTransition(new DetailTransition());

                    // 因为使用add的原因,Material过渡动画只有在进栈时有,返回时没有;
                    // 如果想进栈和出栈都有过渡动画,需要replace,目前库暂不支持,后续会调研看是否可以支持
                    startWithSharedElement(fragment, ((ThirdContentAdapter.VH) vh).img, getResources().getString(R.string.image_transition));
                } else {
                    // 这里如果5.0以下系统调用startWithSharedElement(),会无动画,所以低于5.0,start(fragment)
                    start(fragment);
                }
            }
        });
//        String FoodName,String FoodUnit,String FoodNum,String UnitPrice, int imgRes
        // Init Datas
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < FoodName.length; i++) {

            Article article = new Article(FoodName[i], FoodUnit[i],FoodNum[i],UnitPrice[i],mImgRes[i]);
            articleList.add(article);
        }
        mAdapter.setDatas(articleList);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                         String[] FoodName = new String[]{

                                "白菜111"+mMenu,
                                "豆腐111"
                        };
                         String[] FoodUnit = new String[]{
                                "1个/两111",
                                "2个/两111",

                        };
                         String[] FoodNum = new String[]{
                                "11111",
                                "22111",


                        };
                         String[] UnitPrice = new String[]{
                                "1111",
                                "1112",

                        };

                        List<Article> articleList = new ArrayList<>();
                        for (int i = 0; i < FoodName.length; i++) {

                            Article article = new Article(FoodName[i], FoodUnit[i],FoodNum[i],UnitPrice[i],mImgRes[i]);
                            articleList.add(article);
                        }
                        mAdapter.addItem(articleList);
                        mRefreshLayout.setRefreshing(false);
                        Toast.makeText(_mActivity, "更新了2条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
//滑动监听
        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                if (mScrollTotal <= 0) {
                    mInAtTop = true;
                    Toast.makeText(_mActivity, "上啦加载...", Toast.LENGTH_SHORT).show();
                } else {
//                    mInAtTop = false;
                    Toast.makeText(_mActivity, "下拉刷新...", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(_mActivity, "Action", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    @Override
    public void onRefresh() {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    private void scrollToTop() {
        mRecy.smoothScrollToPosition(0);
    }

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != MainActivity.FIRST) return;

        if (mInAtTop) {
            mRefreshLayout.setRefreshing(true);
            onRefresh();
        } else {
            scrollToTop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecy.setAdapter(null);
        EventBus.getDefault().unregister(this);
    }
}
