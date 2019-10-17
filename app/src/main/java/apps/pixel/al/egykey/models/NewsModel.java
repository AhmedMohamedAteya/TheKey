package apps.pixel.al.egykey.models;


import android.os.Parcel;
import android.os.Parcelable;

public class NewsModel implements Parcelable {
    private String apth ;
    private String isImg ;



    public NewsModel(String apth, String isImg) {
        this.apth = apth;
        this.isImg = isImg;
    }

    public String getApth() {
        return apth;
    }

    public void setApth(String apth) {
        this.apth = apth;
    }

    public String getIsImg() {
        return isImg;
    }

    public void setIsImg(String isImg) {
        this.isImg = isImg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.apth);
        dest.writeString(this.isImg);
    }

    protected NewsModel(Parcel in) {
        this.apth = in.readString();
        this.isImg = in.readString();
    }

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel source) {
            return new NewsModel(source);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };
}
