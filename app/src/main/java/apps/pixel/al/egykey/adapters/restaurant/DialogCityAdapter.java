package apps.pixel.al.egykey.adapters.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;

public class DialogCityAdapter extends RecyclerView.Adapter<DialogCityAdapter.ViewHolder> {

    private final DialogCityAdapter.OnClickHandler onClickHandler;
    private final List<String> listLoactions;
    private Context context;

    public DialogCityAdapter(Context context, List<String> listLoactions, DialogCityAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.listLoactions = listLoactions;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return listLoactions == null ? 0 : listLoactions.size();
    }

    @NonNull
    @Override
    public DialogCityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_layout, parent, false);
        return new DialogCityAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listLoactions.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listLoactions.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final DialogCityAdapter.ViewHolder holder, final int listPosition) {
        holder.mTxtYear.setText(listLoactions.get(listPosition));
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

        private CairoRegularTextView mTxtYear;

        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {
            mTxtYear = itemView.findViewById(R.id.txt_location);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onClick(position);
        }
    }
}



