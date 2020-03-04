package apps.pixel.the.key.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public final class CairoRegularTextView extends AppCompatTextView {


    public CairoRegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CairoRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CairoRegularTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/majalla.ttf");
        setTypeface(tf);

    }

}
