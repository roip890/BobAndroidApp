package com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by User on 22/11/2017.
 */

public class MyTimeTextView extends MyTextView {

    protected int hours, minutes, seconds;
    protected int tempHours, tempMinutes, tempSeconds;
    protected SimpleDateFormat dateFormat;
    protected Calendar calendar;
    protected Boolean fullHours;

    public MyTimeTextView(Context context) {
        this(context, null);
    }

    public MyTimeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.tempHours = 0;
        this.tempMinutes = 0;
        this.tempSeconds = 0;
        this.fullHours = true;
        this.dateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
        this.calendar = Calendar.getInstance();
        this.setHint("Select Time:");
        View dialogView = null;
        LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            dialogView = inflater.inflate(R.layout.dialog_time_picker, null);
            final TimePicker timePicker = dialogView.findViewById(R.id.time_picker);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.setHour(hours);
                timePicker.setMinute(minutes);
                timePicker.setIs24HourView(fullHours);
            }
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    tempHours = hourOfDay;
                    tempMinutes = minute;
                }
            });
            this.setDialogPositiveOnClick(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    hours = tempHours;
                    minutes = tempMinutes;
                    updateText();
                }
            });
            this.setDialogContentView(dialogView);
            this.textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogBuilder.positiveText("set").negativeText("cancel").build().show();
                }
            });
        }
    }

    public void setDateFormat(String format) {
        this.dateFormat.applyPattern(format);
    }

    public void setHours(int hours) {
        this.hours = hours;
        this.updateText();
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        this.updateText();
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        this.updateText();
    }

    private void updateText() {
        calendar.set(Calendar.HOUR, hours);
        calendar.set(Calendar.MINUTE, minutes);
        textView.setText(dateFormat.format(calendar.getTime()));
    }

}
