package apps.pixel.al.egykey.adapters.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lid.lib.LabelImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoBoldTextView;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;

public class OfferResAdapter extends RecyclerView.Adapter<OfferResAdapter.ViewHolder> {

    private final OfferResAdapter.OnClickHandler onClickHandler;
    private List<String> listResNames;
    private List<String> listMealNames;
    private List<String> listImgLocation;
    private List<String> listPresentange;
    private List<String> listImages;
    private Context context;

    public OfferResAdapter(Context context, List<String> listResNames, List<String> listMealNames, List<String> listImgLocation, List<String> listPresentange, List<String> listImages, OfferResAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.listImages = listImages;
        this.listResNames = listResNames;
        this.listMealNames = listMealNames;
        this.listImgLocation = listImgLocation;
        this.listPresentange = listPresentange;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return listMealNames == null ? 0 : listMealNames.size();
    }

    @NonNull
    @Override
    public OfferResAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offer_res, parent, false);
        return new OfferResAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listMealNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listMealNames.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final OfferResAdapter.ViewHolder holder, final int listPosition) {
        try {
            holder.mealName.setText(listMealNames.get(listPosition));
            holder.txtResName.setText(listResNames.get(listPosition));
            holder.txtLoc.setText(listImgLocation.get(listPosition));
            holder.imgMeal.setLabelText(context.getString(R.string.offer) + "  " + listPresentange.get(listPosition) + "  %");
            holder.imgMeal.setLabelTextColor(context.getResources().getColor(R.color.pdlg_color_white));


            Picasso.get()
                    .load(listImages.get(listPosition))
                    .fit()
                    .centerCrop()
                    .into(holder.imgMeal);
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
        void onClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LabelImageView imgMeal;
        private CairoBoldTextView mealName;
        private CairoRegularTextView txtResName;
        private CairoRegularTextView txtLoc;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {

            imgMeal = itemView.findViewById(R.id.img_meal);
            mealName = itemView.findViewById(R.id.meal_name);
            txtResName = itemView.findViewById(R.id.txt_res_name);
            txtLoc = itemView.findViewById(R.id.txt_loc);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onClick(position);
        }
    }
}

