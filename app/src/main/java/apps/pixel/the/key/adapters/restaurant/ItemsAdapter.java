package apps.pixel.the.key.adapters.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.CairoRegularTextView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final ItemsAdapter.OnClickHandler onClickHandler;
    private final List<String> listNames;
    private final List<String> listPrices;
    private final List<String> listImgs;
    private Context context;

    public ItemsAdapter(Context context, List<String> listNames, List<String> listPrices, List<String> listImgs, ItemsAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.listNames = listNames;
        this.listImgs = listImgs;
        this.listPrices = listPrices;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return listNames == null ? 0 : listNames.size();
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);
        return new ItemsAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listNames.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsAdapter.ViewHolder holder, final int listPosition) {
        try {

            holder.name.setText(listNames.get(listPosition));
            holder.desc.setText(listPrices.get(listPosition));

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
        void onItemClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView card;
        private AppCompatImageView img;
        private CairoBoldTextView name;
        private CairoRegularTextView desc;
        private LinearLayoutCompat linearDetails;
        private AppCompatImageView imgBasket;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {

            card = itemView.findViewById(R.id.card);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            linearDetails = itemView.findViewById(R.id.linear_details);
            imgBasket = itemView.findViewById(R.id.img_basket);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onItemClick(position);
        }
    }
}






