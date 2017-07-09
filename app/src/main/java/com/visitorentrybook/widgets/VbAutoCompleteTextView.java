package com.visitorentrybook.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class VbAutoCompleteTextView extends AutoCompleteTextView {

    public VbAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public VbAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VbAutoCompleteTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (isInEditMode() && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            //in the Android Studio layout preview, with SDK < 22, this throws an exception.  You'll
            // only see your custom font in the preview if you have the SDK set to 22 or above.
            return;
        }
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Fonts/" + "OpenSansCondensedLight.ttf");
        setTypeface(tf);
    }
}