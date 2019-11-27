package apps.pixel.al.egykey.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoBoldTextView;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.ViewHolder> {
    private final CurrentAdapter.OnClickHandler onClickHandler;
    private final List<String> listRestaurantNames, listMealNames, listDesc, listImgs, listPrices;
    private Context context;

    public CurrentAdapter(Context context, List<String> listRestaurantNames, List<String> listMealNames, List<String> listPrices, List<String> listDesc, List<String> listImgs, CurrentAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.listRestaurantNames = listRestaurantNames;
        this.listMealNames = listMealNames;
        this.listPrices = listPrices;
        this.listDesc = listDesc;
        this.listImgs = listImgs;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return listRestaurantNames == null ? 0 : listRestaurantNames.size();
    }


    @NonNull
    @Override
    public CurrentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_item, parent, false);
        return new CurrentAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listRestaurantNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listRestaurantNames.size());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CurrentAdapter.ViewHolder holder, final int listPosition) {
        try {

            holder.restaurantName.setText(listRestaurantNames.get(listPosition));
            holder.mealName.setText(listMealNames.get(listPosition));
            holder.txtPrice.setText(listPrices.get(listPosition) + " EGP");
            holder.desc.setText(listDesc.get(listPosition));

            Picasso.get()
                    .load(listImgs.get(listPosition))
                    .fit()
                    .centerCrop()
                    .into(holder.img);


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
        void onItemClickedCurrent(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private AppCompatImageView img;
        private CairoBoldTextView restaurantName;
        private CairoRegularTextView mealName;
        private CairoRegularTextView desc;
        private CairoBoldTextView txtPrice;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {

            img = itemView.findViewById(R.id.img);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            mealName = itemView.findViewById(R.id.meal_name);
            desc = itemView.findViewById(R.id.desc);
            txtPrice = itemView.findViewById(R.id.txt_price);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onItemClickedCurrent(position);
        }
    }
}








