package apps.pixel.al.egykey.adapters.restaurant;

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
import apps.pixel.al.egykey.utilities.CairoBoldTextView;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private final OnClickHandler onClickHandler;
    private final List<String> arabicNames;
    private final List<String> englishNames;
    private final List<String> urlsBackGrounds;
    private final List<String> urlsLogos;
    private final List<String> idList;
    private final List<String> sinceList;
    private Context context;

    public RestaurantAdapter(Context context, List<String> sinceList, List<String> idList, List<String> arabicNames, List<String> englishNames, List<String> urlsBackGrounds, List<String> urlsLogos, OnClickHandler onClickHandler) {
        this.context = context;
        this.arabicNames = arabicNames;
        this.englishNames = englishNames;
        this.sinceList = sinceList;
        this.idList = idList;
        this.urlsLogos = urlsLogos;
        this.urlsBackGrounds = urlsBackGrounds;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return arabicNames == null ? 0 : arabicNames.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item_layout, parent, false);
        return new ViewHolder(view);
    }

    private void removeAt(int position) {
        arabicNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arabicNames.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int listPosition) {

        try {

            holder.titleArabic.setText(arabicNames.get(listPosition));
            holder.titleEnglish.setText(englishNames.get(listPosition));
            holder.mTxtSince.setText(sinceList.get(listPosition));


            Glide.with(context)
                    .load(urlsLogos.get(listPosition))
                    .placeholder(R.drawable.placeholder_logo)
                    .into(holder.imgLogo);

            Glide.with(context)
                    .load(urlsBackGrounds.get(listPosition))
                    .placeholder(R.drawable.test)
                    .into(holder.imgBgRetaurant);


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
        void onClick(String position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatImageView imgBgRetaurant;
        private CircleImageView imgLogo;
        private CairoBoldTextView titleEnglish;
        private CairoBoldTextView titleArabic;
        private CairoRegularTextView mTxtSince;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {
            imgBgRetaurant = itemView.findViewById(R.id.img_bg_retaurant);
            imgLogo = itemView.findViewById(R.id.img_logo);
            titleEnglish = itemView.findViewById(R.id.title_english);
            titleArabic = itemView.findViewById(R.id.title_arabic);
            mTxtSince = itemView.findViewById(R.id.txt_since);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onClick(idList.get(position));
        }
    }
}
