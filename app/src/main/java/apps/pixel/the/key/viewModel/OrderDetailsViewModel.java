package apps.pixel.the.key.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import apps.pixel.the.key.db.OrderDetailsRepository;
import apps.pixel.the.key.models.orderDetails.ExtraOrderDetails;
import apps.pixel.the.key.models.orderDetails.OrderDetailsModel;

public class OrderDetailsViewModel extends AndroidViewModel {
    private OrderDetailsRepository repository;
    private LiveData<List<OrderDetailsModel>> allOrders;
//    private LiveData<OrderDetailsModel> specificOrder;

    public OrderDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderDetailsRepository(application);
        allOrders = repository.getAllOrders();
//        specificOrder = repository.getGetSpecificOrder();
    }

    public void insert(OrderDetailsModel orderDetailsModel) {
        repository.insert(orderDetailsModel);
    }

    public void update(OrderDetailsModel orderDetailsModel) {
        repository.update(orderDetailsModel);
    }

    public void delete(OrderDetailsModel orderDetailsModel) {
        repository.delete(orderDetailsModel);
    }

    public void deleteAllOrders() {
        repository.deleteAllNotes();
    }

    public void deleteSpecificMenu(int id) {
        repository.deleteSpecificData(id);
    }

    public void deleteAllOrdersFromSpecificRestaurant(String restaurantId) {
        repository.deleteALlOrdersFromSpecificRestaurant(restaurantId);
    }

    public void updateSpecificOrder(int id, String restaurantId, String countOfMeals, String mainMealName, String mainMealSize, String mainMealPrice
            , List<ExtraOrderDetails> extraOrderDetails, String description, String hasExtra, String totalPrice,
                                    String arabicName, String englishName, String imgLogo, String selectedId) {
        repository.updateOrder(id, restaurantId, countOfMeals, mainMealName, mainMealSize, mainMealPrice
                , extraOrderDetails, description, hasExtra, totalPrice,
                arabicName, englishName, imgLogo, selectedId);
    }



    public LiveData<List<OrderDetailsModel>> getAllOrders() {
        return allOrders;
    }


//    public LiveData<OrderDetailsModel> getSpecificOrder() {
//        return specificOrder;
//    }
}
