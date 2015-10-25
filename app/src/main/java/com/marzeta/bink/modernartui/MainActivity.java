package com.marzeta.bink.modernartui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView4;
    private ImageView imageView5;

    private int colors[] = new  int[4];//{0xff00ddff, 0xff669900, 0xffcc0000, 0xff00ddff};
    private ImageView imageViews[] = new ImageView[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                for (int i = 0; i<4; i++){
                    imageViews[i].setBackgroundColor(lighten(colors[i], (float) progresValue / 10));
                }
            }

            public int lighten(int color, float factor) {
                int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
                int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
                int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
                return Color.argb(Color.alpha(color), red, green, blue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    // A private method to help us initialize our variables.
    private void initializeVariables() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        imageViews[0] = (ImageView) findViewById(R.id.imageView1);
        imageViews[1] = (ImageView) findViewById(R.id.imageView2);
        imageViews[2] = (ImageView) findViewById(R.id.imageView3);
        imageViews[3] = (ImageView) findViewById(R.id.imageView5);

        colors[0] = getResources().getInteger(R.integer.color1);
        colors[1] = getResources().getInteger(R.integer.color2);
        colors[2] = getResources().getInteger(R.integer.color3);
        colors[3] = getResources().getInteger(R.integer.color1);

        for (int i = 0; i<4; i++){
            imageViews[i].setBackgroundColor(colors[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_info) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://www.moma.org"));
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked cancel button
                        }
                    })
                    .create()
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Context getActivity() {
        return this;
    }
}
