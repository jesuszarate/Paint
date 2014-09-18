package edu.utah.cs4962.paint;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class PaintActivity extends Activity {

    //PaintView _paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PaletteView rootLayout = new PaletteView(this);
        //rootLayout.setOrientation(LinearLayout.VERTICAL);

        for (int splotchIndex = 0; splotchIndex < 10; splotchIndex++) {
            PaintView paintView = new PaintView(this);
            paintView.setBackgroundColor(Color.YELLOW);
            //_paintView.setPadding(5, 5, 5, 5);
            paintView.setColor(Color.DKGRAY);
            rootLayout.addView(paintView, new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.WRAP_CONTENT));

            paintView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PaintView)v).setColor(Color.RED);
                }
            });

            // _paintView.setOnSplotchTouchListener() -> Just a method.
            // new View.OnSplotchTouchListener() -> Interface definition.
            // The rest -> an anonymous class definition. (An anonymous inner class of activity)
            paintView.setOnSplotchTouchListener(new PaintView.OnSplotchTouchListener() {
                @Override
                public void onSplotchTouched(PaintView v) {
                    ((PaintView)v).setColor(Color.DKGRAY);
                }
            });
        }
        setContentView(rootLayout);


    }
}
