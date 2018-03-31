package com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by User on 22/11/2017.
 */

public class MyDateTextView extends MyTextView {

    private int years, months, days;
    private int tempYears, tempMonths, tempDays;
    protected SimpleDateFormat dateFormat;
    protected Calendar calendar;
    protected DatePickerDialog dateDialog;

    public MyDateTextView(Context context) {
        this(context, null);
    }

    public MyDateTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDateTextView(Context context, AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.years = 0;
        this.months = 0;
        this.days = 0;
        this.tempYears = 0;
        this.tempMonths = 0;
        this.tempDays = 0;
        this.dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        this.calendar = Calendar.getInstance();
        this.setHint("Select Date:");


        View dialogView = null;
        LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            dialogView = inflater.inflate(R.layout.dialog_date_picker, null);
            final DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
            datePicker.init(years, months, days, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    tempYears = year;
                    tempMonths = monthOfYear;
                    tempDays = dayOfMonth;
                }
            });
            this.setDialogPositiveOnClick(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    years = tempYears;
                    months = tempMonths;
                    days = tempDays;
                    updateText();
                }
            });
            this.setDialogContentView(dialogView);
            this.textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogBuilder.build().show();
                }
            });
        }
    }

    public void setDateFormat(String format) {
        this.dateFormat.applyPattern(format);
    }

    public void setYears(int years) {
        this.years = years;
        this.updateText();
    }

    public void setMonths(int months) {
        this.months = months;
        this.updateText();
    }

    public void setDays(int days) {
            this.days = days;
        this.updateText();
    }

    private void updateText() {
        calendar.set(Calendar.YEAR, years);
        calendar.set(Calendar.MONTH, months);
        calendar.set(Calendar.DAY_OF_MONTH, days);
        textView.setText(dateFormat.format(calendar.getTime()));
    }
}
