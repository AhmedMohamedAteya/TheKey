package apps.pixel.al.egykey.models.orderDetails;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.al.egykey.db.DataConverter;

@Entity(tableName = "order_details_table")
public class OrderDetailsModel implements Parcelable {
    public static final Parcelable.Creator<OrderDetailsModel> CREATOR = new Parcelable.Creator<OrderDetailsModel>() {
        @Override
        public OrderDetailsModel createFromParcel(Parcel source) {
            return new OrderDetailsModel(source);
        }

        @Override
        public OrderDetailsModel[] newArray(int size) {
            return new OrderDetailsModel[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String restaurantId;
    private String countOfMeals;
    private String mainMealName;
    private String mainMealSize;
    private String mainMealPrice;
    private List<ExtraOrderDetails> extraOrderDetails;
    private String description;
    private String hasExtra;
    private String totalPrice;
    private String englishName;
    private String arabicName;
    private String imgLogo;
    private String selectedId;

    @Ignore
    public OrderDetailsModel() {
    }

    public OrderDetailsModel(String restaurantId, String countOfMeals, String mainMealName, String mainMealSize, String mainMealPrice
            , List<ExtraOrderDetails> extraOrderDetails, String description, String hasExtra, String totalPrice,
                             String arabicName, String englishName, String imgLogo, String selectedId) {
        this.restaurantId = restaurantId;
        this.countOfMeals = countOfMeals;
        this.mainMealName = mainMealName;
        this.mainMealSize = mainMealSize;
        this.mainMealPrice = mainMealPrice;
        this.extraOrderDetails = extraOrderDetails;
        this.description = description;
        this.hasExtra = hasExtra;
        this.totalPrice = totalPrice;
        this.arabicName = arabicName;
        this.englishName = englishName;
        this.imgLogo = imgLogo;
        this.selectedId = selectedId;
    }

    @Ignore
    public OrderDetailsModel(String restaurantId, String countOfMeals, String mainMealName, String mainMealSize, String mainMealPrice, List<ExtraOrderDetails> extraOrderDetails, String description) {
        this.restaurantId = restaurantId;
        this.countOfMeals = countOfMeals;
        this.mainMealName = mainMealName;
        this.mainMealSize = mainMealSize;
        this.mainMealPrice = mainMealPrice;
        this.extraOrderDetails = extraOrderDetails;
        this.description = description;
    }

    protected OrderDetailsModel(Parcel in) {
        this.restaurantId = in.readString();
        this.countOfMeals = in.readString();
        this.mainMealName = in.readString();
        this.mainMealSize = in.readString();
        this.mainMealPrice = in.readString();
        this.extraOrderDetails = new ArrayList<ExtraOrderDetails>();
        in.readList(this.extraOrderDetails, ExtraOrderDetails.class.getClassLoader());
        this.description = in.readString();
    }

    public static Creator<OrderDetailsModel> getCREATOR() {
        return CREATOR;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }

    public String getHasExtra() {
        return hasExtra;
    }

    public void setHasExtra(String hasExtra) {
        this.hasExtra = hasExtra;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCountOfMeals() {
        return countOfMeals;
    }

    public void setCountOfMeals(String countOfMeals) {
        this.countOfMeals = countOfMeals;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainMealName() {
        return mainMealName;
    }

    public void setMainMealName(String mainMealName) {
        this.mainMealName = mainMealName;
    }

    public String getMainMealSize() {
        return mainMealSize;
    }

    public void setMainMealSize(String mainMealSize) {
        this.mainMealSize = mainMealSize;
    }

    public String getMainMealPrice() {
        return mainMealPrice;
    }

    public void setMainMealPrice(String mainMealPrice) {
        this.mainMealPrice = mainMealPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @TypeConverters(DataConverter.class)
    public List<ExtraOrderDetails> getExtraOrderDetails() {
        return extraOrderDetails;
    }

    public void setExtraOrderDetails(List<ExtraOrderDetails> extraOrderDetails) {
        this.extraOrderDetails = extraOrderDetails;
    }

    @Override
    public String toString() {
        return "OrderDetailsModel{" +
                "id=" + id +
                ", restaurantId='" + restaurantId + '\'' +
                ", countOfMeals='" + countOfMeals + '\'' +
                ", mainMealName='" + mainMealName + '\'' +
                ", mainMealSize='" + mainMealSize + '\'' +
                ", mainMealPrice='" + mainMealPrice + '\'' +
                ", extraOrderDetails=" + extraOrderDetails +
                ", description='" + description + '\'' +
                ", hasExtra='" + hasExtra + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", englishName='" + englishName + '\'' +
                ", arabicName='" + arabicName + '\'' +
                ", imgLogo='" + imgLogo + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.restaurantId);
        dest.writeString(this.countOfMeals);
        dest.writeString(this.mainMealName);
        dest.writeString(this.mainMealSize);
        dest.writeString(this.mainMealPrice);
        dest.writeList(this.extraOrderDetails);
        dest.writeString(this.description);
    }

}
