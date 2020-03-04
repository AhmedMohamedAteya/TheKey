package apps.pixel.the.key.adapters.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.CairoBoldTextView;

public class TypesAdapter extends RecyclerView.Adapter<TypesAdapter.ViewHolder> {

    private final TypesAdapter.OnClickHandler onClickHandler;
    private final List<String> listTypes;
    private int pos = -1;
    private Context context;

    public TypesAdapter(Context context, List<String> listTypes, TypesAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.listTypes = listTypes;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return listTypes == null ? 0 : listTypes.size();
    }

    @NonNull
    @Override
    public TypesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        return new TypesAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listTypes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listTypes.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final TypesAdapter.ViewHolder holder, final int listPosition) {
        try {

            AtomicInteger isClicked = new AtomicInteger();
            holder.txtType.setText(listTypes.get(listPosition));


            if (pos == -1) {
                if (listTypes.size() > 0) {
                    pos = 0;
                }
            }

            if (pos == listPosition) {
                holder.item.setBackground(context.getResources().getDrawable(R.drawable.ic_type_rv_selected));
                holder.txtType.setTextColor(context.getResources().getColor(R.color.light_accent));
                //isClicked.set(1);

                    /*else {
                    holder.item.setBackground(context.getResources().getDrawable(R.drawable.ic_type_rv_unslselected));
                    holder.txtType.setTextColor(context.getResources().getColor(R.color.dark_blue));
                    isClicked.set(0);
                }*/
            } else {
                holder.item.setBackground(context.getResources().getDrawable(R.drawable.ic_type_rv_unslselected));
                holder.txtType.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                //isClicked.set(0);
            }


            holder.item.setOnClickListener(v -> {
                pos = listPosition;
                notifyDataSetChanged();
                onClickHandler.onTypeClick(listPosition);
            });

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
        void onTypeClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CairoBoldTextView txtType;
        private FrameLayout item;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {

            txtType = itemView.findViewById(R.id.txt_type);
            item = itemView.findViewById(R.id.item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}





