package apps.pixel.the.key.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.models.orderDetails.ExtraOrderDetails;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.CairoRegularTextView;

public class ItemListBasketAdapter extends ArrayAdapter<ExtraOrderDetails> {

    private final Context context;

    private List<ExtraOrderDetails> extraOrderDetails;

    private CairoRegularTextView number;
    private CairoRegularTextView extraName;
    private CairoBoldTextView remove;
    private CairoBoldTextView add;
    private CairoRegularTextView price;

    public ItemListBasketAdapter(Context context, List<ExtraOrderDetails> extraOrderDetails) {
        super(context, R.layout.basket_list_item, extraOrderDetails);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.extraOrderDetails = extraOrderDetails;
    }

    @Override
    public int getCount() {
        return extraOrderDetails.size();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View rowView = inflater.inflate(R.layout.basket_list_item, null, true);

        initViews(rowView, position);


        return rowView;

    }

    private void initViews(View rowView, int position) {


        number = rowView.findViewById(R.id.number);
        extraName = rowView.findViewById(R.id.extra_name);
//        remove = rowView.findViewById(R.id.remove);
//        add = rowView.findViewById(R.id.add);
        price = rowView.findViewById(R.id.price);

        try {
            number.setText(String.valueOf(position + 1));
            extraName.setText(extraOrderDetails.get(position).getExtraName());
            price.setText(extraOrderDetails.get(position).getExtraPrice() + " " + context.getString(R.string.egp));
        } catch (Exception ignored) {

        }
//        add.setOnClickListener(v -> {
//            Toast.makeText(context, "ADD", Toast.LENGTH_SHORT).show();
//        });
//        remove.setOnClickListener(v -> {
//            Toast.makeText(context, "REMOVE", Toast.LENGTH_SHORT).show();
//        });
    }

}