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

    PaintView _paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        _paintView = new PaintView(this);
        _paintView.setBackgroundColor(Color.YELLOW);
        _paintView.setPadding(5, 5, 5, 5);
        _paintView.setColor(Color.DKGRAY);
        rootLayout.addView(_paintView, new LinearLayout.LayoutParams(500, ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(rootLayout);

        _paintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _paintView.setColor(Color.RED);
            }
        });

        // _paintView.setOnSplotchTouchListener() -> Just a method.
        // new View.OnSplotchTouchListener() -> Interface definition.
        // The rest -> an anonymous class definition. (An anonymous inner class of activity)
        _paintView.setOnSplotchTouchListener(new PaintView.OnSplotchTouchListener() {
            @Override
            public void onSplotchTouched(PaintView view) {
                _paintView.setColor(Color.DKGRAY);
            }
        });
    }
}
