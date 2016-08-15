package me.yokeyword.sample.demo_zhihu.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by YoKeyword on 16/2/1.
 */
public class Article implements Parcelable{
    private String title;
    private String content;
    private String TableID;
    private String EatType;
    private String Water[];
    private String Drink[];
    private String Dry[];
    private int imgRes;

    public String FoodName;
    public String FoodUnit;
    public String FoodNum;
    public String UnitPrice;


    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article(String title, int imgRes) {
        this.title = title;
        this.imgRes = imgRes;
    }
    public Article(String FoodName,String FoodUnit,String FoodNum,String UnitPrice, int imgRes) {
        this.FoodName = FoodName;
        this.FoodUnit = FoodUnit;
        this.FoodNum = FoodNum;
        this.UnitPrice = UnitPrice;
        this.imgRes = imgRes;
    }
//    public Article(String TableID, String EatType,String Water[],String Dry[],String Drink[]) {
//        this.TableID = TableID;
//        this.EatType = EatType;
//        this.Drink = Drink;
//        this.Water = Water;
//        this.Dry = Dry;
//    }

    protected Article(Parcel in) {
        title = in.readString();
        content = in.readString();
        FoodName = in.readString();
        FoodUnit = in.readString();

        FoodNum = in.readString();
        UnitPrice = in.readString();
        imgRes = in.readInt();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getTitle() {
        return title;
    }
    public String getTableID() {
        return TableID;
    }
    public String getEatType() {
        return EatType;
    }
    public String getWater(int i) {
        return Water[i];
    }
    public String getDrink(int i) {
        return Drink[i];
    }
    public String getDry(int i) {
        return Dry[i];
    }

    public String getFoodName() {
        return FoodName;
    }
    public String getFoodNum() {
        return FoodNum;
    }
    public String getFoodUnit() {
        return FoodUnit;
    }
    public String getUnitPrice() {
        return UnitPrice;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(FoodName);
        dest.writeString(FoodUnit);
        dest.writeString(FoodNum);
        dest.writeString(UnitPrice);
        dest.writeInt(imgRes);
    }
}
