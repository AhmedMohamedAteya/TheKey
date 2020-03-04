package apps.pixel.the.key.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public final class CairoRegularEditText extends AppCompatEditText {


    public CairoRegularEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CairoRegularEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CairoRegularEditText(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/majalla.ttf");
        setTypeface(tf);

    }

}
