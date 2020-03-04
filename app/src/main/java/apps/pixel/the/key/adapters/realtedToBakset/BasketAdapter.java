package apps.pixel.the.key.adapters.realtedToBakset;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.adapters.ItemListBasketAdapter;
import apps.pixel.the.key.models.orderDetails.OrderDetailsModel;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.viewModel.OrderDetailsViewModel;

import static android.content.Context.MODE_PRIVATE;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private final BasketAdapter.OnClickHandler onClickHandler;
    private List<OrderDetailsModel> orderDetailsModel;

    private Context context;

    public BasketAdapter(Context context, List<OrderDetailsModel> orderDetailsModel, BasketAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.orderDetailsModel = orderDetailsModel;
        this.onClickHandler = onClickHandler;
    }


    @Override
    public int getItemCount() {
        return orderDetailsModel == null ? 0 : orderDetailsModel.size();
    }

    @NonNull
    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_basket, parent, false);
        return new BasketAdapter.ViewHolder(view);
    }

    public void removeAt(int position) {
        orderDetailsModel.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, orderDetailsModel.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final BasketAdapter.ViewHolder holder, final int listPosition) {
        try {

            holder.mealName.setText(orderDetailsModel.get(listPosition).getMainMealName() + "  - " + orderDetailsModel.get(listPosition).getMainMealSize() + " x "
                    + orderDetailsModel.get(listPosition).getCountOfMeals());
            holder.mTxtPrice.setText(orderDetailsModel.get(listPosition).getMainMealPrice() + " " + context.getString(R.string.egp));

            ItemListBasketAdapter listViewAdapter;
            listViewAdapter = new ItemListBasketAdapter(context, orderDetailsModel.get(listPosition).getExtraOrderDetails());
            holder.listView.setAdapter(listViewAdapter);
            Constant.setListViewHeightBasedOnChildren(holder.listView);
            Log.d("COUNT_", "initViews: " + listViewAdapter.getCount());
        } catch (Exception ignored) {

        }
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public interface OnClickHandler {
        void onClick(int position, int id);

        void onItemDeleted(int position, int id);

        void onItemEdited(int position, int id, String selectedId);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CairoBoldTextView mealName;
        private CairoRegularTextView mTxtPrice;
        private ListView listView;
        private AppCompatImageView imgEdit;
        private AppCompatImageView imgDelete;

        private OrderDetailsViewModel viewModel;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {

            viewModel = ViewModelProviders.of((AppCompatActivity) context).get(OrderDetailsViewModel.class);

            mealName = itemView.findViewById(R.id.meal_name);
            mTxtPrice = itemView.findViewById(R.id.meal_price);
            listView = itemView.findViewById(R.id.list_view);

            imgEdit = itemView.findViewById(R.id.img_edit);
            imgEdit.setOnClickListener(this);

            imgDelete = itemView.findViewById(R.id.img_delete);
            imgDelete.setOnClickListener(this);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                onClickHandler.onClick(position, orderDetailsModel.get(position).getId());

            });

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.img_delete:
                    onClickHandler.onItemDeleted(position, orderDetailsModel.get(position).getId());
                    break;
                case R.id.img_edit:
                    SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, MODE_PRIVATE);

                    sharedPreferences.getString(Constant.SELECTED_SUB_ID_FOR_BOTTOM_DIALOG, null);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    try {
                        if (sharedPreferences.getString(Constant.SELECTED_SUB_ID_FOR_BOTTOM_DIALOG, null).equals(null)) {
                            editor.putString(Constant.SELECTED_SUB_ID_FOR_BOTTOM_DIALOG, orderDetailsModel.get(position).getSelectedId());
                            editor.apply();
                            onClickHandler.onItemEdited(position, orderDetailsModel.get(position).getId(), sharedPreferences.getString(Constant.SELECTED_SUB_ID_FOR_BOTTOM_DIALOG, null));
                        }else {
                            onClickHandler.onItemEdited(position, orderDetailsModel.get(position).getId(), sharedPreferences.getString(Constant.SELECTED_SUB_ID_FOR_BOTTOM_DIALOG, null));
                        }
                        return;
                    }catch (Exception ignored){
                        editor.putString(Constant.SELECTED_SUB_ID_FOR_BOTTOM_DIALOG, orderDetailsModel.get(position).getSelectedId());
                        editor.apply();
                        onClickHandler.onItemEdited(position, orderDetailsModel.get(position).getId(), sharedPreferences.getString(Constant.SELECTED_SUB_ID_FOR_BOTTOM_DIALOG, null));
                    }

                    break;

            }
        }
    }
}



