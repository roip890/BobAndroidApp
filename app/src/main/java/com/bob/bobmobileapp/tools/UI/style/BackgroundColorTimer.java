package com.bob.bobmobileapp.tools.UI.style;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 27/09/2017.
 */

public class BackgroundColorTimer {

    private final int COLORS = 2;
    private final int PRIMARY = 0;
    private final int SECONDARY = 1;
    private final int COLORS_PARAMS = 4;
    private final int HUE = 0;
    private final int SATURATION = 1;
    private final int VALUE = 2;
    private final int ALPHA = 3;

    private Timer timer;
    private Activity activity;
    private ViewGroup backgroundLayout;

    private float[][] originalColors = new float[COLORS][COLORS_PARAMS];
    private float[][] colors = new float[COLORS][COLORS_PARAMS];
    private float[][] intervals = new float[COLORS][COLORS_PARAMS];
    private float[][] ranges = new float[COLORS][COLORS_PARAMS];
    private float[] paramsMax = {360, 1, 1, 256};



    public BackgroundColorTimer(Activity activity, ViewGroup backgroundLayout, int primaryColor, int secondaryColor) {
        this.activity = activity;
        this.backgroundLayout = backgroundLayout;
        this.initColor(PRIMARY, primaryColor);
        this.initColor(SECONDARY, secondaryColor);
        this.timer = new Timer();
        this.ranges[PRIMARY] = new float[]{360, 1, 1, 256};
        this.ranges[SECONDARY] = new float[]{360, 1, 1, 256};
    }

    public void setColorIntervals(int colorIndex, int alphaInterval,int hueInterval, int saturationInterval, int valueInterval) {
        this.intervals[colorIndex][ALPHA] = alphaInterval;
        this.intervals[colorIndex][HUE] = hueInterval;
        this.intervals[colorIndex][SATURATION] = ((float)saturationInterval) / 100;
        this.intervals[colorIndex][VALUE] = ((float)valueInterval) / 100;
    }

    public void setColorIntervalRange(int colorIndex, int alphaRange,int hueRange, int saturationRange, int valueRange) {
        this.ranges[colorIndex][ALPHA] = alphaRange;
        this.ranges[colorIndex][HUE] = hueRange;
        this.ranges[colorIndex][SATURATION] = ((float)saturationRange) / 100;
        this.ranges[colorIndex][VALUE] = ((float)valueRange) / 100;
    }


    public void runTask() {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                incrementColorParam(PRIMARY, ALPHA);
                incrementColorParam(PRIMARY, HUE);
                incrementColorParam(PRIMARY, SATURATION);
                incrementColorParam(PRIMARY, VALUE);
                incrementColorParam(SECONDARY, ALPHA);
                incrementColorParam(SECONDARY, HUE);
                incrementColorParam(SECONDARY, SATURATION);
                incrementColorParam(SECONDARY, VALUE);
                GradientDrawable shapeDrawable = new GradientDrawable();
                shapeDrawable.setColors(new int[]{
                        Color.HSVToColor(colors[SECONDARY]),
                        Color.HSVToColor(colors[SECONDARY]),
                        Color.HSVToColor(colors[PRIMARY])
                });
                shapeDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
                shapeDrawable.setGradientRadius(700);
                shapeDrawable.setShape(GradientDrawable.RECTANGLE);
                backgroundLayout.setBackgroundDrawable(shapeDrawable);
            }
        });
    }

    public void startTimer(int interval) {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runTask();
            }
        }, 0, interval);
    }

    public void stopTimer() {
        this.timer.cancel();
        this.timer.purge();
    }

    private void incrementColorParam(int color, int param) {

        if (param == SATURATION || param == VALUE) {
            float newValue = this.colors[color][param] + this.intervals[color][param];
            if (newValue < this.paramsMax[param] &&  newValue >= 0 && Math.abs(newValue - this.colors[color][param]) < this.ranges[color][param]) {
                this.colors[color][param] = newValue;
            } else {
                this.intervals[color][param] *= -1;
            }
        } else {
            float newValue = this.colors[color][param] + this.intervals[color][param];
            if (newValue < this.paramsMax[param] &&  newValue >= 0 && Math.abs(newValue - this.colors[color][param]) < this.ranges[color][param]) {
                this.colors[color][param] = newValue;
            } else {
                this.intervals[color][param] *= -1;
            }
        }
    }

    private void initColor(int colorIndex, int color) {
        int primaryRed = (color >> 16) & 0xff;
        int primaryGreen = (color >> 8) & 0xff;
        int primaryBlue = (color) & 0xff;
        Color.RGBToHSV(primaryRed, primaryGreen, primaryBlue, this.originalColors[colorIndex]);
        this.originalColors[colorIndex][ALPHA] = (color >> 24) & 0xff;
        Color.RGBToHSV(primaryRed, primaryGreen, primaryBlue, this.colors[colorIndex]);
        this.colors[colorIndex][ALPHA] = (color >> 24) & 0xff;
    }

    public float[][] getColors() {
        return colors;
    }

    public void setColors(float[][] colors) {
        this.colors = colors;
    }

}
