package apps.pixel.the.key.adapters.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.CairoRegularTextView;

public class DialogEducationalAdapter extends RecyclerView.Adapter<DialogEducationalAdapter.ViewHolder> {

    private final DialogEducationalAdapter.OnClickHandler onClickHandler;
    private final List<String> listLoactions;
    private Context context;

    public DialogEducationalAdapter(Context context, List<String> listLoactions, DialogEducationalAdapter.OnClickHandler onClickHandler) {
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
    public DialogEducationalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_education, parent, false);
        return new DialogEducationalAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listLoactions.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listLoactions.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final DialogEducationalAdapter.ViewHolder holder, final int listPosition) {
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

