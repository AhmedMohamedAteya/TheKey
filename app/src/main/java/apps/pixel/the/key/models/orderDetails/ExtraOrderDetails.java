package apps.pixel.the.key.models.orderDetails;

import android.os.Parcel;
import android.os.Parcelable;

public class ExtraOrderDetails implements Parcelable {
    public static final Parcelable.Creator<ExtraOrderDetails> CREATOR = new Parcelable.Creator<ExtraOrderDetails>() {
        @Override
        public ExtraOrderDetails createFromParcel(Parcel source) {
            return new ExtraOrderDetails(source);
        }

        @Override
        public ExtraOrderDetails[] newArray(int size) {
            return new ExtraOrderDetails[size];
        }
    };
    private String extraName;
    private String extraPrice;

    public ExtraOrderDetails(String extraName, String extraPrice) {
        this.extraName = extraName;
        this.extraPrice = extraPrice;
    }

    protected ExtraOrderDetails(Parcel in) {
        this.extraName = in.readString();
        this.extraPrice = in.readString();
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public String getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(String extraPrice) {
        this.extraPrice = extraPrice;
    }

    @Override
    public String toString() {
        return "ExtraOrderDetails{" +
                "extraName='" + extraName + '\'' +
                ", extraSize='" + extraPrice + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.extraName);
        dest.writeString(this.extraPrice);
    }
}
