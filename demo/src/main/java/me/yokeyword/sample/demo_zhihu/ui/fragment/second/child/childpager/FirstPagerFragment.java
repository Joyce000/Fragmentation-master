package me.yokeyword.sample.demo_zhihu.ui.fragment.second.child.childpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo_zhihu.MainActivity;
import me.yokeyword.sample.demo_zhihu.adapter.HomeAdapter;
import me.yokeyword.sample.demo_zhihu.entity.Article;
import me.yokeyword.sample.demo_zhihu.event.TabSelectedEvent;
import me.yokeyword.sample.demo_zhihu.listener.OnItemClickListener;
import me.yokeyword.sample.demo_zhihu.base.BaseFragment;
import me.yokeyword.sample.demo_zhihu.ui.fragment.second.child.DetailFragment;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class FirstPagerFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecy;
    private SwipeRefreshLayout mRefreshLayout;

    private HomeAdapter mAdapter;

    private boolean mAtTop = true;

    private int mScrollTotal;

    private String[] PayType = new String[]{
            "支付宝",
            "现金",
            "支付宝",
            "现金",
            "支付宝",
            "现金",
            "微信"
    };

    private String[] BillPrice = new String[]{
            "1.11",
            "2.22",
            "3.33",
            "1.11",
            "2.22",
            "1.11",
            "2.22",
    };

    public static FirstPagerFragment newInstance() {

        Bundle args = new Bundle();

        FirstPagerFragment fragment = new FirstPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_second_pager_first, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);


        mAdapter = new HomeAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                // 这里的DetailFragment在flow包里
                // 这里是父Fragment启动,要注意 栈层级
                ((SupportFragment) getParentFragment()).start(DetailFragment.newInstance(mAdapter.getItem(position).getTitle()));
            }
        });

        // Init Datas
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < PayType.length; i++) {
//            int index = (int) (Math.random() * 3);
            Article article = new Article(PayType[i], BillPrice[i]);
            articleList.add(article);
        }
        mAdapter.setDatas(articleList);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         String[] PayType = new String[]{
                                "weixin",
                                "支付宝",
                                "现金",
                                "weixin"
                        };

                       String[] BillPrice = new String[]{
                                "111",
                                "222",
                                "333",
                                "111"

                        };
                        List<Article> articleList = new ArrayList<>();
                        for (int i = 0; i < PayType.length; i++) {
//            int index = (int) (Math.random() * 3);
                            Article article = new Article(PayType[i], BillPrice[i]);
                            articleList.add(article);
                        }
                        mAdapter.addItem(articleList);
                        mRefreshLayout.setRefreshing(false);
                        Toast.makeText(_mActivity, "更新了五条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }


        });
        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                if (mScrollTotal <= 0) {
                    mAtTop = true;
                } else {
                    mAtTop = false;
                }
            }
        });
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
        if (event.position != MainActivity.SECOND) return;

        if (mAtTop) {
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
