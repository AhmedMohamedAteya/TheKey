package apps.pixel.al.egykey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.ViewHolder> {

    private final LatestNewsAdapter.OnClickHandler onClickHandler;
    private final List<String> titlesList;
    private final List<String> descList;
    private final List<String> urlsImages;
    private Context context;

    public LatestNewsAdapter(Context context, List<String> descList, List<String> titlesList, List<String> urlsImages, LatestNewsAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.descList = descList;
        this.titlesList = titlesList;
        this.urlsImages = urlsImages;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return titlesList == null ? 0 : titlesList.size();
    }

    @NonNull
    @Override
    public LatestNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_news_item, parent, false);
        return new LatestNewsAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        titlesList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, titlesList.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final LatestNewsAdapter.ViewHolder holder, final int listPosition) {

        try {
            holder.txtNewsDesc.setText(titlesList.get(listPosition));


            Glide.with(context)
                    .load(urlsImages.get(listPosition))
                    .placeholder(R.drawable.place_holder_news)
                    .into(holder.newsImg);


        } catch (NullPointerException ignored) {

        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public interface OnClickHandler {
        void onNewsItemClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatImageView newsImg;
        private CairoRegularTextView txtNewsDesc;

        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {


            newsImg = itemView.findViewById(R.id.txt_news_img);
            txtNewsDesc = itemView.findViewById(R.id.txt_news_desc);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onNewsItemClick(position);
        }
    }
}

