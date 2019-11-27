package apps.pixel.al.egykey.adapters.realtedToBakset;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.models.orderDetails.ExtraOrderDetails;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;

public class ExtraAdapter extends RecyclerView.Adapter<ExtraAdapter.ViewHolder> {

    private final ExtraAdapter.OnClickHandler onClickHandler;
//    private final CheckItems checkItems;
//    private final UnCheckItems unCheckItems;

    private final List<ExtraOrderDetails> extraOrderDetails;
    private final OnItemCheckListener onItemClick;
    private Context context;


    public ExtraAdapter(Context context, List<ExtraOrderDetails> extraOrderDetails, ExtraAdapter.OnClickHandler onClickHandler, OnItemCheckListener onItemCheckListener) {
        this.context = context;
        this.extraOrderDetails = extraOrderDetails;
        this.onClickHandler = onClickHandler;
        this.onItemClick = onItemCheckListener;

    }

    @Override
    public int getItemCount() {
        return extraOrderDetails == null ? 0 : extraOrderDetails.size();
    }

    @NonNull
    @Override
    public ExtraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_extra, parent, false);
        return new ExtraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExtraAdapter.ViewHolder holder, final int listPosition) {
        try {

            holder.txtExtra.setText(extraOrderDetails.get(listPosition).getExtraName());
            holder.txtPrice.setText(extraOrderDetails.get(listPosition).getExtraPrice() + " " + getContext().getString(R.string.egp));


            final ExtraOrderDetails currentItem = extraOrderDetails.get(listPosition);


            holder.itemView.setOnClickListener(v -> {
                holder.checkbox.setChecked(
                        !holder.checkbox.isChecked());
                if (holder.checkbox.isChecked()) {
                    onItemClick.onItemCheck(currentItem, extraOrderDetails.get(listPosition).getExtraPrice());
                } else {
                    onItemClick.onItemUncheck(currentItem, extraOrderDetails.get(listPosition).getExtraPrice());
                }
            });

        } catch (Exception ignored) {
            Log.d("ERROR", "onBindViewHolder: " + ignored.getMessage() + ignored.getCause());
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public interface OnItemCheckListener {
        void onItemCheck(ExtraOrderDetails item, String price);

        void onItemUncheck(ExtraOrderDetails item, String price);
    }


    public interface CheckItems {
        void getCheckItems(int position, String price);
    }

    public interface UnCheckItems {
        void getUnCheckItems(int position, String price);
    }

    public interface OnClickHandler {
        void onTypeClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {//CompoundButton.OnCheckedChangeListener

        private CairoRegularTextView txtExtra;
        private CairoRegularTextView txtPrice;
        private CheckBox checkbox;
        private View itemView;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {
            this.itemView = itemView;
            checkbox = itemView.findViewById(R.id.checkbox);
            checkbox.setClickable(false);

            txtExtra = itemView.findViewById(R.id.txt_extra);
            txtPrice = itemView.findViewById(R.id.txt_price);


            //checkbox.setOnCheckedChangeListener(this);
//            itemView.setOnClickListener(this);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }
//        @Override
//        public void onClick(View view) {
//            int position = getAdapterPosition();
//            onClickHandler.onTypeClick(position);
//        }

//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            if (isChecked) {
//                int position = getAdapterPosition();
//                checkItems.getCheckItems(position, listPrices.get(position));
//            }
//            if (!isChecked) {
//                int position = getAdapterPosition();
//                unCheckItems.getUnCheckItems(position, listPrices.get(position));
//            }
//        }
    }
}






