package apps.pixel.al.egykey.db;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import apps.pixel.al.egykey.models.orderDetails.ExtraOrderDetails;
import apps.pixel.al.egykey.models.orderDetails.OrderDetailsModel;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.utilities.MyApp;

public class OrderDetailsRepository {

    private OrderDetailsDao orderDetailsDao;
    private LiveData<List<OrderDetailsModel>> allOrders;
    private LiveData<OrderDetailsModel> getSpecificOrder;
    private SharedPreferences sharedPreferences;


    public OrderDetailsRepository(Application application) {
        OrderDetailsDatabase database = OrderDetailsDatabase.getInstance(application);
        orderDetailsDao = database.orderDetailsDao();
        allOrders = orderDetailsDao.getAllOrderDetails();
        // getSpecificOrder = orderDetailsDao.getOrderDetails(sharedPreferences.getString(Constant.ID_OF_RESTAURANT_ORDER, "1"));
        sharedPreferences = MyApp.getAppContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);

    }

    public void updateOrder(int id, String restaurantId, String countOfMeals, String mainMealName, String mainMealSize, String mainMealPrice
            , List<ExtraOrderDetails> extraOrderDetails, String description, String hasExtra, String totalPrice,
                            String arabicName, String englishName, String imgLogo, String selectedId) {
        new UpdateSpecificOrderAsyncTask(orderDetailsDao, id, restaurantId, countOfMeals, mainMealName, mainMealSize, mainMealPrice
                , extraOrderDetails, description, hasExtra, totalPrice,
                arabicName, englishName, imgLogo, selectedId).execute();
    }


    public void insert(OrderDetailsModel orderDetailsModel) {
        new InsertOrderAsyncTask(orderDetailsDao).execute(orderDetailsModel);
    }


    public void update(OrderDetailsModel orderDetailsModel) {
        new UpdateOrderAsyncTask(orderDetailsDao).execute(orderDetailsModel);
    }

    public void delete(OrderDetailsModel orderDetailsModel) {
        new DeleteOrderAsyncTask(orderDetailsDao).execute(orderDetailsModel);
    }

    public void deleteAllNotes() {
        new DeleteOrdersNotesAsyncTask(orderDetailsDao).execute();
    }

    public void deleteSpecificData(int id) {
        new DeleteSpecificOrdersAsyncTask(orderDetailsDao, id).execute();
    }

    public void deleteALlOrdersFromSpecificRestaurant(String restaurantId) {
        new DeleteALlOrdersFromSpecificRestaurantAsyncTask(orderDetailsDao, restaurantId).execute();
    }


    public LiveData<List<OrderDetailsModel>> getAllOrders() {
        return allOrders;
    }

//    public LiveData<OrderDetailsModel> getGetSpecificOrder() {
//        return getSpecificOrder;
//    }

    //__________________________________________
    private static class InsertOrderAsyncTask extends AsyncTask<OrderDetailsModel, Void, Void> {
        private OrderDetailsDao orderDetailsDao;

        private InsertOrderAsyncTask(OrderDetailsDao orderDetailsDao) {
            this.orderDetailsDao = orderDetailsDao;
        }

        @Override
        protected Void doInBackground(OrderDetailsModel... orders) {
            orderDetailsDao.insert(orders[0]);
            return null;
        }
    }

    private static class UpdateOrderAsyncTask extends AsyncTask<OrderDetailsModel, Void, Void> {
        private OrderDetailsDao orderDetailsDao;

        private UpdateOrderAsyncTask(OrderDetailsDao orderDetailsDao) {
            this.orderDetailsDao = orderDetailsDao;
        }

        @Override
        protected Void doInBackground(OrderDetailsModel... orders) {
            orderDetailsDao.update(orders[0]);
            return null;
        }
    }

    private static class DeleteOrderAsyncTask extends AsyncTask<OrderDetailsModel, Void, Void> {
        private OrderDetailsDao orderDetailsDao;

        private DeleteOrderAsyncTask(OrderDetailsDao orderDetailsDao) {
            this.orderDetailsDao = orderDetailsDao;
        }

        @Override
        protected Void doInBackground(OrderDetailsModel... orders) {
            orderDetailsDao.delete(orders[0]);
            return null;
        }
    }

    private static class DeleteOrdersNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private OrderDetailsDao orderDetailsDao;

        private DeleteOrdersNotesAsyncTask(OrderDetailsDao orderDetailsDao) {
            this.orderDetailsDao = orderDetailsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDetailsDao.deleteAllNotes();
            return null;
        }
    }


    private static class DeleteALlOrdersFromSpecificRestaurantAsyncTask extends AsyncTask<Void, Void, Void> {
        private OrderDetailsDao orderDetailsDao;
        private String restaurantId;

        private DeleteALlOrdersFromSpecificRestaurantAsyncTask(OrderDetailsDao orderDetailsDao, String restaurantId) {
            this.orderDetailsDao = orderDetailsDao;
            this.restaurantId = restaurantId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDetailsDao.deleteAllOrderFromRestaurant(restaurantId);
            return null;
        }
    }

    private static class DeleteSpecificOrdersAsyncTask extends AsyncTask<Void, Void, Void> {
        private OrderDetailsDao orderDetailsDao;
        private int id;

        private DeleteSpecificOrdersAsyncTask(OrderDetailsDao orderDetailsDao, int id) {
            this.orderDetailsDao = orderDetailsDao;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDetailsDao.deleteRestaurantData(id);
            return null;
        }
    }

    private static class UpdateSpecificOrderAsyncTask extends AsyncTask<Void, Void, Void> {
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
        private String arabicName;
        private String englishName;
        private String imgLogo;
        private String selectedId;
        private OrderDetailsDao orderDetailsDao;

        public UpdateSpecificOrderAsyncTask(OrderDetailsDao orderDetailsDao, int id, String restaurantId, String countOfMeals, String mainMealName
                , String mainMealSize, String mainMealPrice
                , List<ExtraOrderDetails> extraOrderDetails, String description, String hasExtra, String totalPrice,
                                            String arabicName, String englishName, String imgLogo, String selectedId) {
            this.orderDetailsDao = orderDetailsDao;
            this.id = id;
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
            this.orderDetailsDao = orderDetailsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDetailsDao.updateOrderRestaurantData(id, restaurantId, countOfMeals, mainMealName, mainMealSize
                    , mainMealPrice, extraOrderDetails, description, hasExtra, totalPrice
                    , arabicName, englishName, imgLogo, selectedId);
            return null;
        }
    }
}
