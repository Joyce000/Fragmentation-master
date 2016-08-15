package me.yokeyword.sample.demo_zhihu.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo_zhihu.entity.Article;
import me.yokeyword.sample.demo_zhihu.listener.OnItemClickListener;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstHomeAdapter extends RecyclerView.Adapter<FirstHomeAdapter.VH> {
    private List<Article> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    private OnItemClickListener mClickListener;
    public String[] water;
    public String[] drink;
    public String[] dry;
    private int num=0;
    private View view;
    public FirstHomeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
         view = mInflater.inflate(R.layout.item_zhihu_home_first, parent, false);
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
//        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");

        holder.TableID.setText(item.getTitle());
        holder.EatType.setText(item.getContent());

        if (holder.EatType.getTag()==1)
        {
            for(int i=0;i<water.length;i++)
            {
                addRow(context,water[i],holder.Water);
            }
            for(int i=0;i<dry.length;i++)
            {
                addRow(context,dry[i],holder.Dry);

            }
            for(int i=0;i<drink.length;i++)
            {
                addRow(context,drink[i],holder.Drink);
            }
            holder.EatType.setTag(0);
        }
    }
    public void setDatas(List<Article> items,String[] water,String[] dry,String[] drink) {
        mItems.clear();
        mItems.addAll(items);
        this.water = water;
        this.dry = dry;
        this.drink = drink;


    }
    public void addItem(List<Article> items,String[] water,String[] dry,String[] drink)
    {
        this.water = water;
        this.dry = dry;
        this.drink = drink;
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
        public TextView TableID;
        public TextView EatType;
        public TableLayout Water;
        public TableLayout Dry;
        public TableLayout Drink;

        public VH(View itemView) {
            super(itemView);
            TableID = (TextView) itemView.findViewById(R.id.TableID);
            EatType = (TextView) itemView.findViewById(R.id.EatType);
            Water   = (TableLayout) itemView.findViewById(R.id.Water);
            Drink   = (TableLayout) itemView.findViewById(R.id.Drink);
            Dry     = (TableLayout) itemView.findViewById(R.id.Dry);
            EatType.setTag(1);
        }
    }
    public void addRow(Context context,String str,TableLayout tableLayout) {
        TableRow tableRow = new TableRow(context);
       final  TextView textview = new TextView(context);
        textview.setId(num);
       final Button button = new Button(context);
        button.setId(num);
        button.setText(num+"");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
//                TableRow tableRow = (TableRow) view.getParent();
//                TextView textview = (TextView)view.findViewById(num);
//                Button button = (Button) view.findViewById(num);
                textview.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                button.setBackgroundColor(000000);
//                textview.getPaint().setFlags(0);  // 取消设置的的划线
            }
        });
        num++;
        textview.setText(str);
        textview.setTextSize(22);
        textview.setHeight(40);
        button.setWidth(80);
        tableRow.addView(textview);
        tableRow.addView(button);
        tableLayout.addView(tableRow);
    }
}
