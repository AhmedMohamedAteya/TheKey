package apps.pixel.the.key.adapters.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.dialog.restuarant.call.SelectedInterface;
import apps.pixel.the.key.models.RadioButtonModel;
import apps.pixel.the.key.utilities.CairoRegularTextView;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {

    private final CallAdapter.OnClickHandler onClickHandler;
    private final List<String> listLoactions;
    private Context context;
    private List<RadioButtonModel> radioButtonModel = new ArrayList<>();

    private int selectedPosition;


    private RadioButton selectedRadioButton;

    private SelectedInterface selectedInterface;

    public CallAdapter(Context context, List<String> listLoactions, CallAdapter.OnClickHandler onClickHandler, SelectedInterface selectedInterface) {
        this.context = context;
        this.listLoactions = listLoactions;
        this.onClickHandler = onClickHandler;
        this.selectedInterface = selectedInterface;
    }

    @Override
    public int getItemCount() {
        return listLoactions == null ? 0 : listLoactions.size();
    }

    @NonNull
    @Override
    public CallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call_layout, parent, false);
        return new CallAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listLoactions.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listLoactions.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final CallAdapter.ViewHolder holder, final int listPosition) {
        holder.mTxtNum.setText(listLoactions.get(listPosition));

        RadioButton radioButton = holder.radioButton;
        radioButton.setChecked(false);

        radioButton.setOnClickListener(v -> {
            handleingOfRadioButtons(listPosition, v);

            Log.d("SELECTED", "onBindViewHolder: " + listPosition);
            selectedInterface.getSelectedRadioBtn(selectedPosition);
        });
    }

    private void handleingOfRadioButtons(int position, View v) {
        // Set unchecked all other elements in the list, so to display only one selected radio button at a time
        for (RadioButtonModel model : radioButtonModel)
            model.setChecked(false);
        // Set "checked" the model associated to the clicked radio button
        radioButtonModel.add(new RadioButtonModel(true, position));
        selectedPosition = position;
        Log.d("POSITION", "onBindViewHolder: " + selectedPosition + "  __" + position);
        // If current view (RadioButton) differs from previous selected radio button, then uncheck selectedRadioButton
        if (null != selectedRadioButton && !v.equals(selectedRadioButton))
            selectedRadioButton.setChecked(false);
        // Replace the previous selected radio button with the current (clicked) one, and "check" it
        selectedRadioButton = (RadioButton) v;
        selectedRadioButton.setChecked(true);
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

        private RadioButton radioButton;
        private CairoRegularTextView mTxtNum;

        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {
            mTxtNum = itemView.findViewById(R.id.txt_num);
            radioButton = itemView.findViewById(R.id.radio_btn);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onClick(position);
        }
    }
}

