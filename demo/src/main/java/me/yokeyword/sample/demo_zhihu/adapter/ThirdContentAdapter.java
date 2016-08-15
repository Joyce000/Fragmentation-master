package me.yokeyword.sample.demo_zhihu.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo_zhihu.entity.Article;
import me.yokeyword.sample.demo_zhihu.listener.OnItemClickListener;
import me.yokeyword.sample.demo_zhihu.ui.fragment.third.child.ShopFragment;
import me.yokeyword.sample.demo_zhihu.ui.fragment.third.child.child.repalceFoodAdd;

/**
 * Created by Joyes on 2016/8/11.
 */
public class ThirdContentAdapter extends RecyclerView.Adapter<ThirdContentAdapter.VH> {
    private List<Article> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;


    public ThirdContentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.zhihu_third_menu_detail, parent, false);
        final VH holder = new VH(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Article item = mItems.get(position);

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");
        holder.img.setImageResource(item.getImgRes());
        holder.FoodName.setText(item.getFoodName());
        holder.FoodNum.setText(item.getFoodNum());
        holder.FoodUnit.setText(item.getFoodUnit());
        holder.UnitPrice.setText(item.getUnitPrice());

    }

    public void setDatas(List<Article> items) {
        mItems.clear();
        mItems.addAll(items);
    }
    public void addItem(List<Article> items)
    {

        items.addAll(mItems);
        mItems.removeAll(mItems);
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Article getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class VH extends RecyclerView.ViewHolder {
//        public TextView tvTitle;
        public ImageView img;
        public TextView FoodName;
        public TextView FoodNum;
        public TextView UnitPrice;
        public TextView FoodUnit;
        public Button FoodAdd;
        public VH(View itemView) {
            super(itemView);
            FoodName = (TextView) itemView.findViewById(R.id.FoodName);
            img = (ImageView) itemView.findViewById(R.id.FoodPicture);
            FoodUnit = (TextView) itemView.findViewById(R.id.FoodUnit);
            UnitPrice = (TextView) itemView.findViewById(R.id.UnitPrice);
            FoodNum = (TextView) itemView.findViewById(R.id.FoodNum);
            FoodAdd = (Button) itemView.findViewById(R.id.FoodAdd);
//            FoodName = (TextView) itemView.findViewById(R.id.);


        }

    }
}
