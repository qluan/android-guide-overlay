package com.douban.guide.overlay;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;

import com.douban.guide.overlay.library.Shape;


public class MainActivity extends ActionBarActivity {

    ButtonClickGuideOverlay mGuideOverlay;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGuideOverlay = (ButtonClickGuideOverlay) findViewById(R.id.overlay);
        mButton = (Button) findViewById(R.id.button);
        mButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                showButtonGuideOverlay();
            }
        }, 300);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showButtonGuideOverlay() {
        int paddingHorizontal = mButton.getWidth() / 5;
        int paddingVertical = mButton.getHeight() / 4;
        float left = mButton.getX() + paddingHorizontal;
        float right = mButton.getRight() - paddingHorizontal;
        float top = mButton.getY() - paddingVertical;
        float bottom = mButton.getBottom() + paddingVertical;
        mGuideOverlay.show(new Shape.Oval(left, top, right, bottom));
    }
}
