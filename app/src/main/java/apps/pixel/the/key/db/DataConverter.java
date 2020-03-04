package apps.pixel.the.key.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import apps.pixel.the.key.models.orderDetails.ExtraOrderDetails;

public class DataConverter {
    @TypeConverter
    public String fromExtraOrderDetailsList(List<ExtraOrderDetails> extraOrderDetailsList) {
        if (extraOrderDetailsList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ExtraOrderDetails>>() {}.getType();
        String json = gson.toJson(extraOrderDetailsList, type);
        return json;
    }

    @TypeConverter
    public List<ExtraOrderDetails> toExtraOrderDetailsList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ExtraOrderDetails>>() {}.getType();
        List<ExtraOrderDetails> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }
}
