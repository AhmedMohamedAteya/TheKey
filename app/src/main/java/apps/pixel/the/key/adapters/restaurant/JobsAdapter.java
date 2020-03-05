package apps.pixel.the.key.adapters.restaurant;

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

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private final JobsAdapter.OnClickHandler onClickHandler;
    private final List<String> listImgUrls;
    private final List<String> listJobTitles;
    private final List<String> listDates;
    private final List<String> listSalaries;
    private List<String> idList;
    private Context context;

    public JobsAdapter(Context context,
                       List<String> idList
            , List<String> listSalaries
            ,  List<String> listTitles
            , List<String> listImgUrls
            , List<String> listDates
            , JobsAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.idList = idList;
        this.listSalaries = listSalaries;
        this.listImgUrls = listImgUrls;
        this.listDates = listDates;
        this.listJobTitles = listTitles;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return listJobTitles == null ? 0 : listJobTitles.size();
    }

    @NonNull
    @Override
    public JobsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_res, parent, false);
        return new JobsAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listJobTitles.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listJobTitles.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final JobsAdapter.ViewHolder holder, final int listPosition) {
        try {
            holder.txtDate.setText(listDates.get(listPosition));
            holder.jobName.setText(listJobTitles.get(listPosition));
            holder.txtSalary.setText(listSalaries.get(listPosition));


            Picasso.get()
                    .load(listImgUrls.get(listPosition))
                    .fit()
                    .centerCrop()
                    .into(holder.imgJob);
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

        private AppCompatImageView imgJob;
        private CairoBoldTextView jobName;
        private CairoRegularTextView txtDate;
        private CairoRegularTextView txtSalary;

        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {

            txtSalary = itemView.findViewById(R.id.txt_salary);
            imgJob = itemView.findViewById(R.id.img_job);
            jobName = itemView.findViewById(R.id.job_name);
            txtDate = itemView.findViewById(R.id.txt_date);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onClick(position);
        }
    }
}

