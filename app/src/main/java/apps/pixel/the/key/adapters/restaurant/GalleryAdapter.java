package apps.pixel.the.key.adapters.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import apps.pixel.the.key.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private final GalleryAdapter.OnClickHandler onClickHandler;
    private final ArrayList<String> listImgUrls;
    private Context context;

    public GalleryAdapter(Context context, ArrayList<String> listImgUrls, GalleryAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.listImgUrls = listImgUrls;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return listImgUrls == null ? 0 : listImgUrls.size();
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new GalleryAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listImgUrls.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listImgUrls.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryAdapter.ViewHolder holder, final int listPosition) {
        try {
            Picasso.get()
                    .load(listImgUrls.get(listPosition))
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.shape_light_app_color)
                    .into(holder.imgPhoto);
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
        void onClick(String path);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatImageView imgPhoto;

        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {

            imgPhoto = itemView.findViewById(R.id.img_gallery);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onClick(listImgUrls.get(position));
        }
    }
}


