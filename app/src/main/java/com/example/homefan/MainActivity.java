 package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.*;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.LinearInterpolator;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchButton = findViewById(R.id.switchButton);
        imageView = (ImageView) findViewById(R.id.imageView);
        toggleButton = findViewById(R.id.toggleButton);
        seekBar = findViewById(R.id.seekBar);

        rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
        rotateAnimator.setDuration(SPEED[index]);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);
    }

    @Override
    protected void onResume() {
        super.onResume();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                index = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggleButton.isChecked()) {
                    // The toggle is enabled
                    rotateAnimator.setDuration(SPEED[index]);
                    rotateAnimator.start();
                } else {
                    // The toggle is disabled
                    rotateAnimator.end();
                }
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // If the switch button is on
                    gd.setColors(new int[]{ Color.YELLOW , Color.TRANSPARENT });
                    imageView.setBackground(gd);
                }
                else {
                    // If the switch button is off
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }
}