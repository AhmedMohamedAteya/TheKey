package apps.pixel.the.key.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import apps.pixel.the.key.models.orderDetails.ExtraOrderDetails;
import apps.pixel.the.key.models.orderDetails.OrderDetailsModel;

@Dao
public interface OrderDetailsDao {
    @Insert
    void insert(OrderDetailsModel orderDetailsModel);

    @Update
    void update(OrderDetailsModel orderDetails);

    @Delete
    void delete(OrderDetailsModel orderDetails);

    @Query("DELETE FROM order_details_table ")
    void deleteAllNotes();

    @Query("DELETE FROM order_details_table WHERE id = :id")
    void deleteRestaurantData(int id);

    @Query("SELECT * FROM order_details_table WHERE restaurantId =:restaurantId")
    LiveData<OrderDetailsModel> getOrderDetails(String restaurantId);

    @Query("SELECT * FROM order_details_table")
    LiveData<List<OrderDetailsModel>> getAllOrderDetails();

    //    String restaurantId, String countOfMeals, String mainMealName, String mainMealSize, String mainMealPrice
//            , List<ExtraOrderDetails> extraOrderDetails, String description, String hasExtra, String totalPrice,
//    String arabicName, String englishName, String imgLogo, String selectedId

    @Query("DELETE FROM order_details_table WHERE restaurantId = :restaurantId")
    void deleteAllOrderFromRestaurant(String restaurantId);

    @Query("UPDATE order_details_table SET restaurantId=:restaurantId , countOfMeals = :countOfMeals , mainMealName = :mainMealName " +
            ", mainMealSize = :mainMealSize , mainMealPrice = :mainMealPrice , extraOrderDetails = :extraOrderDetails ," +
            " description=:description ,hasExtra = :hasExtra ,totalPrice = :totalPrice , arabicName =:arabicName " +
            ",englishName = :englishName , imgLogo =:imgLogo , selectedId =:selectedId WHERE id = :id")
    void updateOrderRestaurantData(int id, String restaurantId, String countOfMeals, String mainMealName, String mainMealSize, String mainMealPrice
            , List<ExtraOrderDetails> extraOrderDetails, String description, String hasExtra, String totalPrice,
                                   String arabicName, String englishName, String imgLogo, String selectedId);


}
