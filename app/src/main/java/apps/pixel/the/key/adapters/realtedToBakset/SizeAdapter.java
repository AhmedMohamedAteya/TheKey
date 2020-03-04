package apps.pixel.the.key.adapters.realtedToBakset;

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
import apps.pixel.the.key.models.RadioButtonModel;
import apps.pixel.the.key.utilities.CairoRegularTextView;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    private final SizeAdapter.OnClickHandler onClickHandler;
    private final List<String> listSizes;
    private final List<String> listPrices;
    private List<RadioButtonModel> radioButtonModel = new ArrayList<>();
    private Context context;
    private int selectedPosition;
    private SelectedInterface selectedInterface;
    private RadioButton selectedRadioButton;


    public SizeAdapter(Context context, List<String> listSizes, List<String> listPrices, SizeAdapter.OnClickHandler onClickHandler, SelectedInterface selectedInterface) {
        this.context = context;
        this.listSizes = listSizes;
        this.listPrices = listPrices;
        this.onClickHandler = onClickHandler;
        this.selectedInterface = selectedInterface;
    }

    @Override
    public int getItemCount() {
        return listPrices == null ? 0 : listPrices.size();
    }

    @NonNull
    @Override
    public SizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_price, parent, false);
        return new SizeAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listPrices.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listPrices.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final SizeAdapter.ViewHolder holder, final int listPosition) {
        try {

            holder.txtPrice.setText(listPrices.get(listPosition) + " " + getContext().getString(R.string.egp));
            holder.txtSize.setText(listSizes.get(listPosition));


            RadioButton radioButton = holder.radioBtn;
            radioButton.setChecked(false);

            radioButton.setOnClickListener(v -> {
                handleingOfRadioButtons(listPosition, v);

                Log.d("SELECTED", "onBindViewHolder: " + listPosition);
                selectedInterface.getSelectedRadioBtn(selectedPosition, listSizes.get(listPosition));
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

    public interface SelectedInterface {
        void getSelectedRadioBtn(int position, String size);

    }

    public interface OnClickHandler {
        void onSizeSelected(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CairoRegularTextView txtSize;
        private CairoRegularTextView txtPrice;
        private RadioButton radioBtn;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {


            txtSize = itemView.findViewById(R.id.txt_size);
            txtPrice = itemView.findViewById(R.id.txt_price);
            radioBtn = itemView.findViewById(R.id.radio_btn);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onSizeSelected(position);
        }
    }
}





