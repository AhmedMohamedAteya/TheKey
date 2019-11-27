package apps.pixel.al.egykey.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import apps.pixel.al.egykey.models.orderDetails.OrderDetailsModel;
import apps.pixel.al.egykey.utilities.Constant;

@Database(entities = {OrderDetailsModel.class}, version = 33, exportSchema = false)
@TypeConverters({DataConverter.class})
public abstract class OrderDetailsDatabase extends RoomDatabase {
    private static OrderDetailsDatabase instance;

    public static synchronized OrderDetailsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OrderDetailsDatabase.class, Constant.DATA_BASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract OrderDetailsDao orderDetailsDao();
}
