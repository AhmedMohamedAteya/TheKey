package apps.pixel.the.key.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

public final class CairoBoldButton extends AppCompatButton {


    public CairoBoldButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CairoBoldButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CairoBoldButton(Context context) {
        super(context);
        init();
    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/majallab.ttf");
        setTypeface(tf);

    }

}
