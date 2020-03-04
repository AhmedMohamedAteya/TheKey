package apps.pixel.the.key.adapters;

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

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.CairoRegularTextView;

public class PerviousAdapter extends RecyclerView.Adapter<PerviousAdapter.ViewHolder> {
    private final OnClickHandlerPervious onClickHandlerPervious;
    private final List<String> listRestaurantNames, listMealNames, listDesc, listImgs, listPrices;
    private Context context;

    public PerviousAdapter(Context context, List<String> listRestaurantNames, List<String> listMealNames, List<String> listPrices, List<String> listDesc, List<String> listImgs, OnClickHandlerPervious onClickHandlerPervious) {
        this.context = context;
        this.listRestaurantNames = listRestaurantNames;
        this.listMealNames = listMealNames;
        this.listPrices = listPrices;
        this.listDesc = listDesc;
        this.listImgs = listImgs;
        this.onClickHandlerPervious = onClickHandlerPervious;
    }

    @Override
    public int getItemCount() {
        return listRestaurantNames == null ? 0 : listRestaurantNames.size();
    }


    @NonNull
    @Override
    public PerviousAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pervious, parent, false);
        return new PerviousAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listRestaurantNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listRestaurantNames.size());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final PerviousAdapter.ViewHolder holder, final int listPosition) {
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

    public interface OnClickHandlerPervious {
        void onItemClickedPervious(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private CairoBoldTextView restaurantName;
        private CairoRegularTextView mealName;
        private CairoRegularTextView desc;
        private CairoBoldTextView txtPrice;
        private AppCompatImageView img;


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
            onClickHandlerPervious.onItemClickedPervious(position);
        }
    }
}



