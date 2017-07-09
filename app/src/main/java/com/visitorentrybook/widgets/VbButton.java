package com.visitorentrybook.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;

import com.visitorentrybook.R;


/**
 * Created by Satish on 8/8/2016.
 */
public class VbButton extends Button {

    public VbButton(Context context) {
        super(context);
    }

    public VbButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs, 0);
        if (!isInEditMode())
            initWithAttrs(context, attrs, 0, 0);
    }

    public VbButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs, defStyleAttr);
        if (!isInEditMode())
            initWithAttrs(context, attrs, defStyleAttr, 0);

    }

    @SuppressWarnings("unused")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VbButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs, defStyleAttr);
        if (!isInEditMode())
            initWithAttrs(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initWithAttrs(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VbButton, defStyleAttr, defStyleRes);
        int customFontIndex = a.getInt(R.styleable.VbButton_setButtonFont, -1);
        if (customFontIndex != -1) {
            String fontPath = getResources().getStringArray(R.array.FontNames)[customFontIndex];
            setCustomFont(fontPath);
        }
        a.recycle();
    }

    /**
     * Loads a font from the given asset path
     *
     * @param customFontPath path in the assets folder to the font file
     */
    private void setCustomFont(@NonNull String customFontPath) {
        if (isInEditMode() && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            //in the Android Studio layout preview, with SDK < 22, this throws an exception.  You'll
            // only see your custom font in the preview if you have the SDK set to 22 or above.
            return;
        }
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "Fonts/" + customFontPath);
        setTypeface(typeface);
    }

    private void setup(Context context, AttributeSet attrs, int defStyle) {
        setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}
